module Authentication
  include Smv  
  
  #validating whether the user is logged in or not
  
  def logged_in?    
    logger.info("Authentication::Logged In::sesssion:---- #{session}")
    if session[:userEmail].blank?
       invalid_session = I18n.t("message.no_session") 
       smv_status = {
                  :statusCode => -1,
                  :value => '',          
                  :msg => "#{invalid_session}"
       }
    else
         smv_status = {
                  :statusCode => 0,
                  :value => '',          
                  :msg => ''
         }       
    end
    logger.info("Authentication::Logged In::status--- #{smv_status}")
    if smv_status[:statusCode] ==-1
      logger.error("Authentication::Logged In::status--- #{smv_status}")
       return false
    else 
       return true
    end
    
  end
  
  #getting the current logged in user details
  def current_user 
      if logged_in?
        if session[:userEmail]
          user = {"email"=>session[:userEmail]}
          params = {"user" => user}
          logger.info("Authentication::current_user::params---#{params}")
          begin
              @logged_in_user_info = smvGetUser(params,request.env)
              smv_status = {
                 :statusCode => @logged_in_user_info.statusCode,
                 :value => @logged_in_user_info.value,          
                 :msg => @logged_in_user_info.message
              }
              logger.info("Authentication::current_user::smvGetUser::status---#{smv_status}")          
          rescue Exception => exc
                 smv_status = {
                  :statusCode => -1,
                  :value => '',          
                  :msg => "Java API is throwing some exception:-- #{exc.message}"
                 }
          end
        end
      else
        invalid_session = I18n.t("message.no_session") 
        smv_status = {
                  :statusCode => -1,
                  :value => '',          
                  :msg => "#{invalid_session}"
        }
      end
      return smv_status
  end
  
  #logging the user with username password provided
  def require_login auth_data
    username = auth_data[:login][:email]
    password = auth_data[:login][:password]    
    if username && password
        begin
           user  = {"email"=>username, "password"=>password}
           newParams = {"user" => user}
           logger.info("Authentication::Params---#{newParams}")
           @userSession = smvLogin(newParams, request.env)
           smv_status = {
              :statusCode => @userSession.statusCode,
              :value => @userSession.value,          
              :msg => @userSession.message
           }
           logger.info("Authentication::require_login::userSession---#{smv_status}")
           if smv_status[:statusCode].blank? || smv_status[:statusCode]==0
                 session[:userSession] = smv_status[:value].sessionGuid
                 session[:userEmail]= username
                 current_user_status = current_user
                 if current_user_status[:statusCode]==-1 
                      smv_status =current_user_status
                      loggedOut
                 end
           end
        rescue Exception => exc
          logger.info("Authentication::User Session:-- #{session[:userSession]}, #{exc.message}") 
          smv_status = {
            :statusCode => -1,
            :value => '',          
            :msg => "Java API is throwing some exception:-- #{exc.message}"
          }
        end
     else
        smv_status = {
            :statusCode => -1,
            :value => '',          
            :msg => 'Please enter a valid username and password'
        }
      end
      return smv_status
  end
  
  def self.included(base)
    base.send :helper_method, :current_user, :logged_in?
  end
  
  def loggedOut
    reset_session    
  end
  
  
  #checking whether the user already exists in database or not
  def validateUser valid_data
     begin     
      user_email = valid_data['user']
      logger.info("Authentication::ValidateUser:---#{user_email}")
      @userValid = smvIsUserExist(user_email, request.env)      
       smv_status = {
              :statusCode => @userValid.statusCode,
              :value => @userValid.value,          
              :msg => @userValid.message
       }
      logger.info("Authentication::ValidateUser::smvIsUserExist:---#{smv_status}")
      return smv_status
    rescue Exception => exc
      logger.error("validateUser::Message for the log file #{exc.message}")
      smv_status = {
            :statusCode => -1,
            :value => '',          
	    :msg => "Java API is throwing some exception:-- #{exc.message}"
      }
      return  smv_status 
    end     
  end  
  
end