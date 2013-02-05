

class CatalogController < ApplicationController

 include Authentication

 
 ##This is the first function called
  def index    
    logger.info("CatalogController::index::")
    begin 
          outlet
          @catalog_info = getAllProducts(request.env)
          cc_value =  @catalog_info.value          
          @smv_status = {
                :statusCode => @catalog_info.statusCode,
                :value => cc_value,          
                :msg => @catalog_info.message
          }
          logger.info("CatalogController::index----#{@smv_status}")
    rescue  Exception => exc
          @error_message = I18n.t("message.product_fetch_error")
          @smv_status = {
                :statusCode => -1,
                :value => '',          
          :msg => "Java API error occured::#{@error_message}::#{exc.message}"
              }      
          logger.error("CatalogController::index-----#{@smv_status}")
          return @smv_status
    end
    return @smv_status
  end
 
end
