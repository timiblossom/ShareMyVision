

class ItemController < ApplicationController
 
 layout "main"
 include Authentication
 before_filter :isLoggedIn
   
 def isLoggedIn
   if !logged_in?
     redirect_to :controller=>'user', :action=> 'index'  
   end
 end
 
# def index   
#    logger.info("ItemController::Index")
#     render :action => 'event'
#  end
  
  def event    
    begin 
      outlet 
      if current_user[:statusCode].blank? || current_user[:statusCode]==0
         input = {"user"=>{"userId"=>current_user[:value].userId}}   
	       logger.info("ItemController::Event::Input----#{input}")
         @events_info = smvGetEventsByUser(input, request.env) 
         @smv_status = {
            :statusCode => @events_info.statusCode,
            :value => @events_info.value,          
	          :msg => @events_info.message
          }
      else
         @smv_status = {
            :statusCode => -1,
            :value => '',          
	          :msg => "No User Found"
          }
      end
      logger.info("ItemController::Event::----#{@smv_status}")
      @numberOfInactiveEvents =0
      if !@smv_status.blank? && @smv_status[:statusCode]==0
          @smv_status[:value].each do |event|
            if event.status.downcase != I18n.t("constant.event_active").downcase
              @numberOfInactiveEvents = @numberOfInactiveEvents + 1
            end
          end
      end
      logger.info("ItemController::Event::number of inactive events----#{@numberOfInactiveEvents}")
      
    rescue  Exception => exc
      @error_message = I18n.t("message.event_fetch_error")
      @smv_status = {
            :statusCode => -1,
            :value => '',          
	          :msg => "Java API error occured::#{@error_message}::#{exc.message}"
          }      
      logger.error("ItemController::Event-----#{@smv_status}")      
    end
    return @smv_status
  end
  
  

  #updating event description
  def updateEvent    
    input = {"event"=>{"eventId"=>params[:eventId].to_i,"eventDescription"=>params[:eventDescription]}}
    logger.info("ItemController::updateEvent:params-----#{input}")
    begin
      if input['event']['eventDescription'].blank?
        @smv_status = {
                    :statusCode => -1,
                    :value => '',          
                    :msg => "Please enter description!"
        }      
        logger.error("ItemController::updateEvent-----#{@smv_status}")
      else
        @status = smvUpdateEvent(input,request.env)
        @smv_status = {
                            :statusCode => @status.statusCode,
                            :value =>  @status.value,          
                            :msg => @status.message
        }   
      end        
    rescue  Exception => exc
      @smv_status = {
                          :statusCode => -1,
                          :value => '',          
                          :msg => exc.message
      }
      logger.error("ItemController::updateEvent-----#{@smv_status}:#{exc.message}")  
    end 
    if @smv_status[:statusCode]!=-1
       render :json => {'status' =>@smv_status[:statusCode],'errorCode'=>@smv_status[:value], 'errorMesage'=>@smv_status[:msg],'successMessage'=>''}, :layout => false
    else
      render :json => {'status' =>@smv_status[:statusCode],'errorCode'=>@smv_status[:value], 'errorMesage'=>'','successMessage'=>@smv_status[:msg]}, :layout => false            
    end
    #render  :update do |page|
    #                if @smv_status[:statusCode]!=-1
    #                  page[:login].hide
    #                  page.refresh 
    #                else 
    #                    page.replace_html 'login_error_msg',   @smv_status[:msg]         
    #                    page[:login_error_msg].show
    #                   page[:login_error_msg].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
    #                    page[:login_error_msg].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
    #                end  
    #end
    
  end 
  

  #deleting delete
  def deleteEvent    
    input = {"event"=>{"eventId"=>params[:eventId].to_i,"eventStatus"=>params[:status]}}
    logger.info("ItemController::deleteEvent:params-----#{input}")
    begin
      if input['event']['eventId'].blank?
        @smv_status = {
                    :statusCode => -1,
                    :value => '',          
                    :msg => "Please set eventId!"
        }      
        logger.error("ItemController::deleteEvent-----#{@smv_status}")
      elsif input['event']['eventStatus'].blank?
        @smv_status = {
                    :statusCode => -1,
                    :value => '',          
                    :msg => "Please set status!"
        }      
        logger.error("ItemController::deleteEvent-----#{@smv_status}")
      else
        @status = smvUpdateEvent(input,request.env)
        @smv_status = {
                            :statusCode => @status.statusCode,
                            :value =>  @status.value,          
                            :msg => @status.message
        }   
      end        
    rescue  Exception => exc
      @smv_status = {
                          :statusCode => -1,
                          :value => '',          
                          :msg => exc.message
      }
      logger.error("ItemController::deleteEvent-----#{@smv_status}:#{exc.message}")  
    end 
    if @smv_status[:statusCode]==-1
       render :json => {'status' =>@smv_status[:statusCode],'errorCode'=>@smv_status[:value], 'errorMesage'=>@smv_status[:msg],'successMessage'=>''}, :layout => false
    else
      render :json => {'status' =>@smv_status[:statusCode],'errorCode'=>@smv_status[:value], 'errorMesage'=>'','successMessage'=>@smv_status[:msg]}, :layout => false            
    end
    #render  :update do |page|
    #                if @smv_status[:statusCode]!=-1
    #                  page[:login].hide
    #                  page.refresh 
    #                else 
    #                    page.replace_html 'login_error_msg',   @smv_status[:msg]         
    #                    page[:login_error_msg].show
    #                   page[:login_error_msg].visual_effect :pulsate, :queue => {:position => 'end', :scope => 'flash'}
    #                    page[:login_error_msg].visual_effect :fade, :queue => {:position => 'end', :scope => 'flash'}
    #                end  
    #end
    
  end  
 
  
  #updating event privacy
  def updatePrivacy
      if params[:eventPublic] == "true"
        eventPrivacy = false
      elsif params[:eventPublic] =="false"
        eventPrivacy = true  
      end    
      input = {"event"=>{"eventId"=>params[:eventValueId].to_i,"public"=>eventPrivacy}}
      logger.info("ItemController::updatePrivacy:params-----#{input}")
      begin
        if input['event']['eventId'].blank?  
          @smv_status = {
                      :statusCode => -1,
                      :value => '',          
                      :msg => "Please set eventId!"
          }      
          logger.error("ItemController::updatePrivacy-----#{@smv_status}")
        end
        if !input['event']['eventId'].blank?           
            @status = smvUpdateEvent(input,request.env)
            @smv_status = {
                                :statusCode => @status.statusCode,
                                :value =>  @status.value,          
                                :msg => @status.message
            }
        end  
      rescue  Exception => exc
        @smv_status = {
                            :statusCode => -1,
                            :value => '',          
                            :msg => exc.message
        }
        logger.error("ItemController::updatePrivacy Exception-----#{@smv_status}:#{exc.message}")  
      end 
      if @smv_status[:statusCode]==-1
         render :json => {'status' =>@smv_status[:statusCode],'errorCode'=>@smv_status[:value], 'errorMesage'=>@smv_status[:msg],'successMessage'=>''}, :layout => false
      else
        render :json => {'status' =>@smv_status[:statusCode],'errorCode'=>@smv_status[:value], 'errorMesage'=>'','successMessage'=>@smv_status[:msg]}, :layout => false            
      end      
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
end
