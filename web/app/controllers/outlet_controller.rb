
class OutletController < ApplicationController

  include Authentication  
##This is the first function called
  def index
    logger.info("OutletController::index")
    begin 
          @outlet_info = smvGetOutlet(request.env) 
          logger.info(@outlet_info.value)         
          @smv_status = {
                :statusCode => @outlet_info.statusCode,
                :value => @outlet_info.value,          
                :msg => @outlet_info.message
          }
          logger.info("OutletController::index----#{@smv_status}")
    rescue  Exception => exc
          @error_message = I18n.t("message.outlet_fetch_error")
          @smv_status = {
                :statusCode => -1,
                :value => '',          
                :msg => "Java API error occured::#{@error_message}::#{exc.message}"
          }      
          logger.error("OutletController::index-----#{@smv_status}")
          return @smv_status
    end
    return @smv_status
  end
end
