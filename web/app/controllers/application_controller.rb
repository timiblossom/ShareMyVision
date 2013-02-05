# Filters added to this controller apply to all controllers in the application.
# Likewise, all the methods added will be available for all controllers.

class ApplicationController < ActionController::Base
  include Smv

  
  layout "main"
  #Pick a unique cookie name to distinguish our session data from others'
  session :session_key => '_smv_session_id'
  helper_method :current_user, :logged_in?, :getHomePageImages,:getHostName,:outlet
  
 def index   
     logger.info("ApplicationController::Index")
     if !logged_in?
        redirect_to :controller=>'user', :action=> 'list'  
     end
  end
 
 #Function to retrieve home page Images 
 def getHomePageImages  
     image_name = []
     @response = smvGetFeatureImages(params, request.env)
     @images = @response.getValue()
     logger.info("getHomePageImages::---#{@images}")
     @images.each do |val| 
        name = "[\"http://img.sharemyvision.com/theme1/homepage/"+val+".jpg\",\"\",\"\",\"\"]";
        image_name.push(name)        
        logger.info("getHomePageImages::Images:----#{name}")        
     end
     if image_name.empty?
       logger.info("getHomePageImages::No Image returned")
     end
     logger.info("getHomePageImages::Image json:--#{image_name}")
     return image_name
 end
 
 
 
 #LogIn Function
 def loggedInNavigation
   render :update do |page|
                    page.replace_html 'flash',   "User logged in successfully"         
                    page[:flash].show
                    page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                    page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}     
       		    page.redirect_to(:controller => 'item', :action => 'event')
   end
 end
 
 #Logout Function
 def loggedOutNavigation

   render :update do |page|
                    page.replace_html 'flash',   "User logged out successfully"         
                    page[:flash].show
                    page[:flash].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
                    page[:flash].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
                    page.redirect_to(:controller => 'user', :action => 'index')
   
   end
 end

 #sending Mail 
 def sendMail email_params
   logger.info("ApplicationController:sendMail:#{email_params}")
   begin
      @response = smvSendMail(email_params, request.env)
      smv_mail_status = {
                               :statusCode => @response.statusCode,
                               :value => @response.value,          
                               :msg => @response.message
      }
      if  @response.statusCode !=0
        logger.error("ApplicationController:sendMail:#{smv_mail_status}")
      end
     logger.info("ApplicationController:sendMail status:#{smv_mail_status}")         
   rescue Exception => exc
     msg_text = I18n.t("message.mail_error")
     smv_status = {
                :statusCode => -1,
                :value => '',          
               :msg => exc.message
        }
     logger.error("ApplicationController:sendMail:#{smv_status}")
   end     
 end
 
 def getHostName
  return smvHostName() 
 end 
 
 def outlet
   logger.info("ApplicationController::index")
   @error_message = I18n.t("message.outlet_fetch_error")
      begin 
            if logged_in?               
               @outlet_info = smvGetOutletForUser(current_user[:value].userId.to_i,request.env)
               logger.info("ApplicationController::smvGetOutletForUser----#{@outlet_info}")
            else
               @outlet_info = smvGetOutlet(request.env)
               logger.info("ApplicationController::smvGetOutlet----#{@outlet_info}")                
            end
           
            if @outlet_info.blank?
              @smv_outlet_status = {
                                :statusCode => -1,
                                :value => '',          
                                :msg => @error_message
              } 
              logger.error("ApplicationController::getOutlet----#{@smv_outlet_status}")
            else
              @smv_outlet_status = {
                    :statusCode => @outlet_info.statusCode,
                    :value => @outlet_info.value,          
                    :msg => @outlet_info.message
              }
              logger.info("ApplicationController::getOutlet----#{@smv_outlet_status}")
            end  
      rescue  Exception => exc            
            @smv_outlet_status = {
                  :statusCode => -1,
                  :value => '',          
                  :msg => "Java API error occured::#{@error_message}::#{exc.message}"
            }      
            logger.error("ApplicationController::getOutlet-----#{@smv_outlet_status}")
            return @smv_outlet_status
      end
   return @smv_outlet_status
 end
 
end
