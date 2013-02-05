

class UserController < ApplicationController

 include Authentication
 
 URL = "/web/user"

 
 ##This is the first function called
  def index
    getMostRecentItems
    outlet    
    render :action => 'list' 
  end
 
  ##This is called when the end user clicks on login 
  def login
       login_status = require_login params  ##This is called when the end user clicks on login
       logger.info("UserController::login::status:---#{login_status}")
       if login_status[:statusCode].blank? || login_status[:statusCode]==0
              if logged_in?
                  loggedInNavigation  ##When the authentication goes well login boxes gets hide and logout button is displayed by this function
              else
	          render  :update do |page|
                     page.replace_html 'flash',   login_status[:msg]         
                     page[:flash].show
                     page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                     page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
                 end
              end
       else
            render  :update do |page|
                     page.replace_html 'flash',   login_status[:msg]                              
                     page[:flash].show
                     page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                     page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
            end           
       end       
  end

  
 
  ##This function is used for registration of the new user
  def register
    logger.info("UserController::register")
    if logged_in?
       redirect_to :controller=>'user' , :action=>'my_account'
    end     
  end

  ##This function is used for saving the registration values into the DB
  def save_reg
    val = verify_recaptcha() ##This is for verifiction of captcha
    logger.info("UsercController::save_reg:Captcha:-----#{val} params:-----#{params}")
    if !val
        msg_text = I18n.t("message.captcha_not_verified")
        smv_status = {
                  :statusCode => -1,
                  :value => '',          
                  :msg => "Error Message:-- #{msg_text}"
        }
        logger.error("UserController::save_reg::CaptchaStatus:---#{smv_status}")
        render :json => {'status' =>'inactive', 'activation_code' => '', 'errorCode'=>smv_status[:statusCode], 'errorMesage'=>msg_text }, :layout => false
        return 
    end
     begin
       @user = smvRegister(params, request.env) ##API is called for saving the registration values 
       smv_status = {
                 :statusCode => @user.statusCode,
                 :value => @user.value,          
                 :msg => @user.message
       }
       if smv_status[:statusCode]==0 || smv_status[:statusCode].blank?
          #sending mail to the user
          toName = ""           
          if !params['user']['firstName'].blank?            
            toName = toName + params['user']['firstName'] +" "  
          end
          if !params['user']['lastName'].blank?
            toName = toName + params['user']['lastName']
          end
          url="http://#{request.host}/web/accountActivation/#{smv_status[:value].userActivationCode}" 
          input = {"mailer"=>{"email"=>params['user']['email'],"name"=>toName,"eventCode"=>I18n.t("constant.event_regisration_code"),"body"=>{"name"=>toName,"key"=>"registrationLink","url"=>url},"subject"=>"Account Registration Email"}}     
          #sending mail to the user from application controller
          sendMail input
          render :json => {'status' =>'active', 'activation_code' => smv_status[:value].userActivationCode, 'errorCode'=>'', 'errorMesage'=>''}, :layout => false
       else
          logger.error("UsercController::save_reg::error:---#{smv_status}")
          render :json => {'status' =>'inactive', 'activation_code' => '','errorCode'=>smv_status[:statusCode], 'errorMesage'=>smv_status[:msg]}, :layout => false
       end
    rescue Exception => exc      
      msg_text = I18n.t("message.registration_error")
      smv_status = {
                 :statusCode => -1,
                 :value => '',          
                :msg => exc.message
          }
      logger.error("UserController::save_reg::Exception-----#{msg_text}::----#{smv_status}")
      render :json => {'status' =>'inactive', 'activation_code' => '', 'errorCode'=>smv_status[:statusCode], 'errorMesage'=>smv_status[:msg]}, :layout => false
    end   
  end

  ##When user click on the activation url in the email, then this function is being called
  def activate   
     logger.info("UserController::Activate::Params-----#{params}")
     @update_message=""
     begin
         input = {"user"=>{"activationCode"=>params['id']}}
         logger.info("UserController::Activate:---Input #{input}")
         @activate = smvActivateUser(input, request.env)
         logger.info("UserController::Activate:---#{@activate}")
         @update_message = I18n.t("message.successful_activation")
     rescue Exception => exc
        @update_message = I18n.t("message.unsuccessful_activation")
        logger.error("save_reg::Exception occured #{@update_message}")
     end       
  end

  ##When user click on the forgot password activation url in the email
  def generatePassword
      logger.info("UserController::generatePassword::Params-----#{params}")
      @activationCode  =''
      if !params['id'].blank?
         @activationCode  = params['id']
      end
  end

  ##Updating the forgot password submitted by the user
  def update_password_form
      logger.info("UserController::update_password_form:params:---#{params}")      
      begin
           err =0;
           err_message =''
           if  params['user']['password'].blank? 
             err_message = 'New password cannot be blank'
             err=1
           elsif  params['user']['password_confirmation'].blank?
             err_message = 'Confirm password cannot be blank'
             err=1
           elsif  params['user']['password_confirmation']!=params['user']['password']
             err_message = 'New and confirm password does not match'
             err=1
           elsif  params['user']['PasswordResetUserCode'].blank?
             err_message = 'Please enter a valid url'
             err=1
           end
           @valid = smvChangeForgottenPassword(params,request.env)
            smv_status = {
                         :statusCode => @valid.statusCode,
                         :value => @valid.value,          
                         :msg => @valid.message
           }
           if  smv_status[:statusCode]==0 || smv_status[:statusCode].blank?
             logger.info("UserController::update_password_form:smvChangeForgottenPassword:---#{smv_status}")
              err=0
              render :update do |page|
                           page.show 'password_sucess'
                           page['password_sucess'].innerHTML=I18n.t("message.password_reset_success_message")
                           page.replace_html 'flash',   I18n.t('message.password_reset_success_message')         
                           page[:flash].show
                           page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                           page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
                           page.hide 'forgot_password'
                           page.visual_effect :highlight, 'password_sucess'
              end
           else
             logger.error("UserController::update_password_form:smvChangeForgottenPassword:---#{smv_status}") 
             err=1
             err_message =smv_status[:msg]
           end
           if err==1
             render  :update do |page|
                        page.replace_html 'flash',   "#{err_message}"
                        page[:submit].show
                        page[:password_sucess].hide
                        page[:flash].show
                        page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                        page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
             end                                      
           end  
      rescue Exception => exc
         msg_text = I18n.t("message.forgot_password_error")
         smv_status = {
                    :statusCode => -1,
                    :value => '',          
                   :msg => exc.message
         }
         logger.error("UserController::ForgotPassword:#{smv_status}")
         render  :update do |page|        
                    page.replace_html 'flash',   "#{smv_status[:msg]}"         
                    page[:submit].show
                    page[:password_sucess].hide
                    page[:flash].show
                    page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                    page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
         end
      end
  end
  
  ##This function is for changing the password of the logged In user
  def change_password
    
  end 
  
  
  
  ##If the old/new registered user has forgotten password
  def forgot_password
    logger.info("UserController::ForgotPassword")
    if logged_in?
       redirect_to :controller=>'user' , :action=>'my_account'
    end
  end
  
  ##If the old/new registered user has forgotten password, this function will send an email and change the password also.
  def forgot_password_submit     
        begin  
              user = params["user"] 
              input = {"user"=>user["email"]}
              isEmailValid = validateUser input  #Checking whether the user already exist or not
              ## if isEmailValid[:statusCode] =0 and value =false, means user doesn't exist
              ## if isEmailValid[:statusCode] =-1 unexpected error occured
              ## if isEmailValid[:statusCode] =0  and value =true, means user exist in our database
              
              logger.info("UserController::forgot_password_submit:---#{isEmailValid}")
              err = 0                          
              if isEmailValid[:statusCode].blank? || (isEmailValid[:statusCode]==0 && isEmailValid[:value] == true)
                logger.info("UserController::smvForgetPassword:params:---#{params}")
                @isPasswordChanged  = smvForgetPassword(params, request.env) ##API is called for retrieving the forgotten password
                smv_status = {
                                    :statusCode => @isPasswordChanged.statusCode,
                                    :value => @isPasswordChanged.value,          
                                    :msg => @isPasswordChanged.message
                }
                if smv_status[:statusCode].blank? || smv_status[:statusCode]==0
                    logger.info("UserController::forgot_password_submit:smvForgetPassword:---#{smv_status}")                                       
                    render :update do |page|
                                  page.show 'password_sucess'
                                  page['password_sucess'].innerHTML=I18n.t("message.password_reset_message")
                                  page.replace_html 'flash',   I18n.t('message.password_reset_message')         
                                  page[:flash].show
                                  page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                                  page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
                                  page.hide 'forgot_password'
                                  page.visual_effect :highlight, 'password_sucess'
                     end
                else
                  err = 1     
                end
              else
                err = 1   
              end
             if err ==0
               #sending email to the end user
               url = "http://#{request.host}#{URL}/generatePassword/#{smv_status[:value].userPasswordResetUserCode}"               
               input = {"mailer"=>{"email"=>params['user']['email'],"name"=>"User","eventCode"=>I18n.t("constant.event_forgotpassword_code"),"body"=>{"name"=>"User","key"=>"passwordResetLink","url"=>url},"subject"=>"Password Reset Link"}}
               logger.info('UserController::forgot_password_submit:sendMail:params:#{input}')                           
               sendMail input
             end
             if err == 1                   
                 logger.error("UserController::forgot_password_submit:smvForgetPassword:---#{smv_status}")
                 render  :update do |page|
                        if  isEmailValid[:statusCode] !=0                            
                             page.replace_html 'flash',   "#{isEmailValid[:statusCode]}"
                        elsif isEmailValid[:statusCode]==0 && isEmailValid[:value]==false
                             page.replace_html 'flash',   "user does not exist"
                        else
                          page.replace_html 'flash',   "#{smv_status[:statusCode]}"
                        end                     
                        page[:submit].show
                        page[:password_sucess].hide
                        page[:flash].show
                        page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                        page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
                 end
             end    
         rescue Exception => exc
             msg_text = I18n.t("message.forgot_password_error")
             smv_status = {
                        :statusCode => -1,
                        :value => '',          
                       :msg => exc.message
             }
             logger.error("UserController::ForgotPassword:#{smv_status}")
             render  :update do |page|        
                        page.replace_html 'flash',   "#{smv_status[:msg]}"         
                        page[:submit].show
                        page[:password_sucess].hide
                        page[:flash].show
                        page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                        page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
             end                         
         end  
  end
  
  def forgotPasswordCheckEmail
     begin
        isUserValidInfo = validateUser params
        
        if isUserValidInfo[:statusCode] != 0
          status = 'inactive'
          successsMessage = ''
          errorCode=smv_status[:statusCode]
          errorMessage=smv_status[:msg]
        elsif isUserValidInfo[:statusCode] == 0 && isUserValidInfo[:value]==false  
          status = 'inactive'
          successsMessage = ''
          errorCode=''
          errorMessage='User doesn\'t exist'
       else  
          status = 'active'
          successsMessage = ''
          errorCode=''
          errorMessage=''       
        end
    rescue Exception => exc
          msg_text = I18n.t("message.forgot_password_error")
          smv_status = {
                     :statusCode => -1,
                     :value => '',          
                    :msg => exc.message
          }
       logger.error("UserController::forgotPasswordCheckEmail:---#{isUserValidInfo}")              
    end     
      render :json => {'status' =>status,'errorCode'=>errorCode, 'errorMesage'=>errorMessage,'successMessage'=>successsMessage}, :layout => false
  end
  
  ##Function to check whether the email already exist with the system or not.
  def check_email
     isUserValidInfo = validateUser params    
      smv_status = {
            :statusCode => isUserValidInfo[:statusCode],
            :value => isUserValidInfo[:value],          
            :msg => isUserValidInfo[:msg]
        } 
     logger.info("UserController::check_email:---#{smv_status}")     
     if smv_status[:statusCode] ==0 
       if smv_status[:value] == true
         status = 'inactive'
         successsMessage = ''
         errorCode=smv_status[:statusCode]
         errorMessage="User Already Exists"
       else
         status = 'active'
         successsMessage = smv_status[:msg]
         errorCode=smv_status[:statusCode]
         errorMessage=''
      end
     else
       status = 'inactive'
       successsMessage = ''
       errorCode=smv_status[:statusCode]
       errorMessage=smv_status[:msg]
     end
     render :json => {'status' =>status,'errorCode'=>errorCode, 'errorMesage'=>errorMessage,'successMessage'=>successsMessage}, :layout => false
  end
  
  private
  
  def printdebug
    ss = request.env['RAW_POST_DATA']
    puts ss
  end
  
  def getSessionId
      if request.env["HTTP_SESSION"].blank?
         return session[:id]
      end
      return request.env["HTTP_SESSION"]
  end
  
  public
  
  
  ##Function to logout the user from the current session
  def logout
    reset_session  
    refreshLogout ##This will hide the logout button and display the login box
       
  end

 ##This will hide the logout button and display the login box
 def refreshLogout
   loggedOutNavigation
 end
 
 ##Function shows the account Information page to the logged In user
 def my_account
   if !logged_in?
       redirect_to :controller=>'user' , :action=>'list'
    end
 end
 
 
 
  ##This function is for saving the new password of the user
 def change_password_submit
   logger.info("UserController::ChangePasswordSubmit:---#{params}")
   err =0
   err_message=''
   @params_pass = params['user']
     
   if @params_pass['password'].blank?
     err=1
     err_message='Password cannot be blank'
   elsif   @params_pass['password'] != current_user[:value].userPassword
     err=1
     err_message='Please enter a valid old password'
   elsif  @params_pass['newPassword'].blank?    
     err=1
     err_message='New password cannot be blank'
   end
   if  @params_pass['password_confirmation'].blank?
     err=1
     err_message='Confirm password cannot be blank'
   end
   if  @params_pass['password_confirmation']!=@params_pass['newPassword']
     err=1
     err_message='New and confirm password does not match'
   end
   if  @params_pass['password_confirmation']==@params_pass['newPassword']
     input = {"user"=>{"email"=>current_user[:value].userEmail, "password"=>@params_pass['password'],"newPassword"=>@params_pass['newPassword']}}       
     begin
         @valid = smvChangePassword(input,request.env)
          smv_status = {
                 :statusCode => @valid.statusCode,
                 :value => @valid.value,          
                 :msg => @valid.message
         }
         if smv_status[:statusCode]==0 || smv_status[:statusCode].blank?
            err=0           
            logger.info("UserController::change_password_submit::smvChangePassword:---#{smv_status}")
            render :update do |page|
                                             page.show 'change_password_success'
                                             page['change_password_success'].innerHTML=I18n.t("message.change_password_success_message")
                                             page.replace_html 'flash',   I18n.t('message.change_password_success_message')         
                                             page[:flash].show
                                             page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                                             page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
                                             page.hide 'change_password'
                                             page.visual_effect :highlight, 'password_sucess'
            end
         else
           err=1
           err_message =smv_status[:msg]
         end
         
     rescue Exception => exc
          smv_status = {
                 :statusCode => -1,
                 :value => '',          
                 :msg => exc.message
          }   
          logger.error("UserController::change_password_submit::Exception---------#{smv_status}")
         err=1
         err_message =smv_status[:msg]         
     end
   end
   if err==1
     render  :update do |page|        
                page.replace_html 'flash',   "#{err_message}"         
                page[:submit].show
                page[:change_password_success].hide
                page[:flash].show
                page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
     end     
   end
 end

 
 ##This function is used for displaying the devices
 def device
   logger.info("UserController::device:---#{params}")
 end
 
##This function is used for downloading the android app
  def download
    logger.info("UserController::download:---#{params}")
  end
   
 #Update Profile

 def update_info
   logger.info("UserController::UpdateProfile:---#{current_user[:value].userEmail}")

  if current_user[:value].contact.contactBillingAddress.blank? 
     @billingStreet=''
     @billingZipCode=''
     @billingState=''
     @billingCountry=''
     @billingCity=''
  else
     @billingStreet=current_user[:value].contact.contactBillingAddress.street
     @billingZipCode=current_user[:value].contact.contactBillingAddress.zipCode
     @billingState=current_user[:value].contact.contactBillingAddress.state
     @billingCountry=current_user[:value].contact.contactBillingAddress.country
     @billingCity=current_user[:value].contact.contactBillingAddress.city
  end
  if current_user[:value].contact.contactShippingAddress.blank? 
     @shippingStreet=''
     @shippingZipCode=''
     @shippingState=''
     @shippingCountry=''
     @shippingCity=''
  else
     @shippingStreet=current_user[:value].contact.contactShippingAddress.street
     @shippingZipCode=current_user[:value].contact.contactShippingAddress.zipCode
     @shippingState=current_user[:value].contact.contactShippingAddress.state
     @shippingCountry=current_user[:value].contact.contactShippingAddress.country
     @shippingCity=current_user[:value].contact.contactShippingAddress.city
  end

 end

  def getMostRecentItems
    begin
      input={"num"=>10}
      logger.info("UserController::getMostRecentItems::Input:----#{input}")
      @items_info =smvGetMostRecentItems(params, request.env)
      @smv_status = {
            :statusCode => @items_info.statusCode,
            :value => @items_info.value,          
            :msg => @items_info.message
          }
    logger.info("UserController::getMostRecentItems::Result:----#{@smv_status}")
    rescue Exception => exc
      @smv_status = {
            :statusCode => -1,
            :value => '',          
            :msg => "Java API error occured::#{exc.message}"
          }      
      logger.error("UserController::getMostRecentItems::---Unable to fetch Items for the user|event:--#{@smv_status}")
    end
  end
  
  #This function is used for validating the facebook access_token
  def oAuthValidate
    logger.info("UserController::oAuthValidate::Params:----#{params}")
        
  end
  
end
