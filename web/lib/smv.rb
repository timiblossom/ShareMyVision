require "java"

require "smv_common"





module Smv

   def smvProcessRequest(params, env, sessionId)
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
       env["SESSION"] = sessionId
       aggregation.processRequest(params, env)
   end
   

   def smvLogin(params, env)
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
       
       aggregation.login(SmvUtil::ObjectConverter.mapToUserDTO(params), env)
   end
   

   def smvLogOut(params, env)
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
       aggregation.logout(SmvUtil::ObjectConverter.mapToUserDTO(params), env)
   end
   

   def smvRegister(params, env)
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
       aggregation.register(SmvUtil::ObjectConverter.mapToUserDTO(params), env)
   end
   

   def smvNewSessionDTO
       SmvBean::SessionDTO.new
   end
   
   
   def smvGetUser(params, env)
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()    
     aggregation.getUser(SmvUtil::ObjectConverter.mapToUserDTO(params), env)     
   end
   

   def smvIsUserExist(email, env)
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()    
     aggregation.isUserExist(email, env)     
   end
 
   
   def smvForgetPassword(params, env)
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()         
     aggregation.forgotPassword(SmvUtil::ObjectConverter.mapToUserDTO(params), env)     
   end
   
  def smvChangeForgottenPassword(params, env)
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()         
       aggregation.changeForgottenPassword(SmvUtil::ObjectConverter.mapToUserDTO(params), env)     
  end
   
  
   def smvChangePassword(params, env)
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()    
     userDTO = SmvUtil::ObjectConverter.mapToUserDTO(params)
     newPassword =  SmvUtil::ObjectConverter.mapToNewPassword(params)
     aggregation.changePassword(userDTO, newPassword, env)
   end
   
 
   def smvActivateUser(params, env)
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()      
     aggregation.activateUser(SmvUtil::ObjectConverter.mapToActivationCode(params), env)
   end
   

   def regenerateActivationCode(params, env)
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()      
     aggregation.regenerateActivationCode(SmvUtil::ObjectConverter.mapToUserDTO(params), env)
   end
   

   def smvGetFeatureImages(params, env)
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
     aggregation.getFeatureImages(env)
   end
   
 
   def smvGetEventsByUser(params, env)
     logger.info("smvGetEventsByUser #{params}")
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()      
     aggregation.getEventsByUser(SmvUtil::ObjectConverter.mapToUserDTO(params), SmvUtil::ObjectConverter.mapToShowIems(params), env)
   end
   
 
   def smvGetItemsByEvent(params, env)
       logger.info("smvGetItemsByEvent #{params}")
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()     
       aggregation.getItemsByEvent(SmvUtil::ObjectConverter.mapToEventDTO(params), env)
   end
   
 
   def smvGetEventById(params, env)
   	   logger.info("smvGetItemsByEvent #{params}")
   	   aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()
   	   aggregation.getEventById(SmvUtil::ObjectConverter.mapToEventId(params), env)

   end
   
   
   def smvGetMostRecentItemsByUser(params, env) 
       logger.info("smvGetItemsByEvent #{params}")  
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()        
       aggregation.getMostRecentItemsByUser(SmvUtil::ObjectConverter.mapToUserDTO(params), 10, env)   
   end
   
   
   def smvGetMostRecentItems(params, env)   
       logger.info("smvGetItemsByEvent #{params}")
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
       aggregation.getMostRecentItems(10, env)        
   end
   
   
   def smvGetItemById(params, env)   
       logger.info("smvGetItemsByEvent #{params}")
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
       aggregation.getItemById(SmvUtil::ObjectConverter.mapToItemId(params), env)
   end
   
   
   def smvUpdateItem(params, env)   
          logger.info("smvUpdateItem #{params}")
          aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
          aggregation.udpateItem(SmvUtil::ObjectConverter.mapToItemDTO(params), env)
   end
   
   
   def smvUpdateEvent(params, env)   
          logger.info("smvUpdateEvent #{params}")
          aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
          aggregation.updateEvent(SmvUtil::ObjectConverter.mapToEventDTO(params), env)
   end
   
   def getAllProducts(env)
     logger.info("getAllProducts #{params}")
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance() 
     aggregation.getAllProducts(env)
   end
   
   def smvSendMail(params, env)
     logger.info("smv::sendMail #{params}")
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()
     aggregation.sendMail(SmvUtil::ObjectConverter.mapToMailerDataDTO(params), env)
   end
  
   def smvHostName()
     logger.info("smvHostName #{params}")     
     aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()
     return WebConfig::ResourceManager.getSmvHostName()
   end
   
  def smvGetOutlet(env)
       logger.info("smvGetOutlet")     
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()
       aggregation.getOutlet(env)       
  end
  
  def smvGetOutletForUser(userId, env)
       logger.info("smvGetOutletForUser")     
       aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()
       aggregation.getOutletForUser(userId, env)       
  end
  
 # def smvEnableOutletForUser(params, env)
 #      logger.info("smvEnableOutletForUser")     
 #      aggregation = SmvAggregator::AggregationFactory.getAggregationInstance()
 #      aggregation.enableOutletForUser(SmvUtil::ObjectConverter.mapToUserOutletDTO(params), env)       
 # end
   
   
end
