

class GalleryController < ApplicationController
 include Authentication
 
 layout "gallery"
 

 def isLoggedIn
   if !logged_in?
     redirect_to :controller=>'user', :action=> 'list'  
   end
 end
 
# def index   
#    logger.info("ItemController::Index")
#     render :action => 'event'
#  end
  

  def imagegallery
    outlet 
    #decode = Base64.decode64(params['id']).split("|")
    decode = params['id']
    begin
      input={"event"=>{"eventId"=>params['id'].to_i}}
      logger.info("ItemController::ImageGallery::Input:----#{input}")
  
      @items_info  = smvGetItemsByEvent(input,request.env)      
      @smv_status = {
            :statusCode => @items_info.statusCode,
            :value => @items_info.value,          
	          :msg => @items_info.message
          }
      logger.info("ItemController::ImageGallery::Result:----#{@smv_status}")
      @event_info  = smvGetEventById(input,request.env)
      @smv_event_status = {
           :statusCode => @event_info.statusCode,
           :value => @event_info.value,          
           :msg => @event_info.message
      }
      logger.info("ItemController::ImageGallery::Event Result:----#{@smv_event_status}")
    rescue Exception => exc
      @smv_status = {
            :statusCode => -1,
            :value => '',          
	          :msg => "Java API error occured::#{exc.message}"
          }      
      logger.error("ItemController::ImageGallery::---Unable to fetch Items for the user|event:--#{@smv_status}")
    end
  end 
  
end
