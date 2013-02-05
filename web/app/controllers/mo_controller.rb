

class MoController < ApplicationController
 
  layout nil 

  def initevent
        @sessionId = "someid"
        @output = smvProcessRequest(params, request.env, @sessionId)
        render :json => @output
  end

  def upsertevent

  end
 
  def index   
    #puts getSessionId
   # @items = smvFiles
    logger.info("ItemController::Index")
    render :action => 'event'
  end
  
 
  def ce
        
        @output = smvProcessRequest(params, request.env, getSessionId)
        logger.info("Raw Post Data : #{request.env['RAW_POST_DATA']}")
        render :json => @output
  end

  def filerequest   
      
        @output = smvProcessRequest(request.env['RAW_POST_DATA'], request.env, getSessionId)
        render :json => @output
  end

  def userrequest        
        @output = smvProcessRequest(request.env['RAW_POST_DATA'], request.env, getSessionId)
        render :json => @output
  end

  def processResponse(obj)
    doc = REXML::Document.new
    root = doc.add_element( "AddressResponse" )

    city = root.add_element( "City" )
    city.add_attribute( "rating", "10" )
    city.add_attribute( "category", "good" )

    city_name = city.add_element( "CityName" )
    city_name.add_text( obj.city  )

    state = root.add_element( "State" )
    state.add_text( obj.state )

    doc.write( out_string = "", 2 )
    return out_string

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
