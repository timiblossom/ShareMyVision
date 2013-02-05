require "java"

module SmvAggregator
   include_package "com.smv.service.aggregator"
end

module WebConfig
    include_package "com.smv.service.aggregator.config"
end

module SmvBean
        include_package "com.smv.common.bean"        
end

module JavaLang
       include_package "java.lang"
end


module SmvUtil

   class ObjectConverter

  	 def ObjectConverter.mapToUserDTO(params)

       userMap = params["user"]
       userDTO = SmvBean::UserDTO.new
       
       userDTO.setAction(userMap["commit"])
       userDTO.setUserEmail(userMap["email"])
       userDTO.setUserPassword(userMap["password"])
       userDTO.setUserId(userMap["userId"])       
       userDTO.setUserStatus(userMap["status"])
       userDTO.setUserPasswordResetUserCode(userMap["PasswordResetUserCode"])

       contactDTO = SmvBean::ContactDTO.new
       contactDTO.setContactLastName(userMap["lastName"])
       contactDTO.setContactFirstName(userMap["firstName"])
       userDTO.setContact(contactDTO)

       return userDTO

     end
   
   
     def ObjectConverter.mapToRegenerateActivationCode(params)
        userMap = params["user"]
        userMap["activationCode"]    
     end


     def ObjectConverter.mapToActivationCode(params)
        userMap = params["user"]
        userMap["activationCode"]  
     end
   
   
     def ObjectConverter.mapToNewPassword(params)
        userMap = params["user"]
        userMap["newPassword"]           
     end
   
		
     def ObjectConverter.mapToEventDTO(params)
       eventMap = params["event"]
       eventDTO = SmvBean::EventDTO.new
       eventDTO.setAction(eventMap["commit"])
       eventDTO.setEventCode(eventMap["eventCode"])
       eventDTO.setEventDescription(eventMap["eventDescription"])
       eventDTO.setEventTitle(eventMap["eventTitle"])
       eventDTO.setAccountId(eventMap["accountId"])
       eventDTO.setEventId(eventMap["eventId"])
       eventDTO.setEventPublic(eventMap["public"])
       eventDTO.setStatus(eventMap["eventStatus"])
       eventDTO.setEventTagText(eventMap["eventTags"])
       eventDTO.setUserId(eventMap["userId"])
   
       return eventDTO   
     end


     def ObjectConverter.mapToItemDTO(params)
            itemMap = params["item"]
            itemDTO = SmvBean::ItemDTO.new
            itemDTO.setItemCode(eventMap["itemCode"])
            itemDTO.setItemDescription(eventMap["itemDescription"])
            itemDTO.setItemTitle(eventMap["itemTitle"])            
            
            itemDTO.setItemId(eventMap["itemId"])
            itemDTO.setUserId(eventMap["userId"])
            itemDTO.setStatus(eventMap["itemStatus"])
     
        
            return itemDTO   
     end
     
   
     def ObjectConverter.mapToShowIems(params)
        eventMap = params["event"]
	    if eventMap.nil? || eventMap["showItems"].nil?
	      return JavaLang::Boolean::FALSE
	    end
	    JavaLang::Boolean.new(eventMap["showItems"])	
     end
      
    
     def ObjectConverter.mapToItemId(params)
        itemMap = params["item"]
        
        if itemMap.nil? || itemMap["itemId"].nil?
        	return JavaLang::Long.valueOf(0)
        end
        JavaLang::Long.new(itemMap["itemId"])
     end   
   
   
     def ObjectConverter.mapToEventId(params)
       eventMap = params["event"]
       if eventMap.nil? || eventMap["eventId"].nil?
       	  return JavaLang::Long.valuOf(0)
       end
       JavaLang::Long.new(eventMap["eventId"])      
     end
    
    # def ObjectConverter.mapToMailerDataDTO(params)       
    #     mailerMap = params["mailer"]                
    #     mailerDTO  =  SmvBean::MailerDataDTO.new
    #     mailerDTO.setMailerEventCode(mailerMap["eventCode"])           
    #     mailerDTO.setFromEmail('a@a.com')
    #     mailerDTO.setFromName('pankaj')
         
    #     key1DTO = SmvBean::KeyValueMapDTO.new
    ##     #key1DTO = SMVBean::KeyValueEntry.new
     #    keyDTO  = key1DTO.KeyValueEntry.new
     #    keyDTO.setKey('name')
     #    keyDTO.setValue('test')
     #   mailerDTO.setSubjectData(keyDTO)
         
     #    keyDTO =SmvBean::KeyValueMapDTO.new
     #    keyDTO.setKey('name')
     #    keyDTO.setValue(mailerMap["body"])
     #    mailerDTO.setBodyData(keyValueDTO)  
     #    keyDTO.setKey('registrationCode')
     #    keyDTO.setValue(mailerMap["body"])  
     #    mailerDTO.setBodyData(keyDTO)
     #        
     #    emailDTO = SmvBean::EmailNameMapDTO.new
     #    emailDTO.setName(mailerMap["name"])
     #    emailDTO.setEmail(mailerMap["email"])
     #    mailerDTO.setTo(emailDTO)
           
     #  return mailerDTO            
     #end*/
     
    def ObjectConverter.mapToMailerDataDTO(params)
      mailerMap = params["mailer"]
      mailerDTO  =  SmvBean::MailerDataDTO.new
      mailerDTO.setMailerEventCode(mailerMap["eventCode"])
        
      WebConfig::ResourceManager.getSmvEmailAddress()  
      mailerDTO.setFromEmail(WebConfig::ResourceManager.getSmvEmailAddress())
      mailerDTO.setFromName(WebConfig::ResourceManager.getSmvEmailName())

      subjectMapDTO = SmvBean::KeyValueMapDTO.new
      keyDTO  = SmvBean::KeyValueEntryDTO.new

      keyDTO.setKey('name')
      keyDTO.setValue(mailerMap["subject"])
      subjectMapDTO.getEntries().add(keyDTO)
      
      mailerDTO.setSubjectData(subjectMapDTO )
      
      bodyMapDTO = SmvBean::KeyValueMapDTO.new
      key1DTO = SmvBean::KeyValueEntryDTO.new
      key1DTO.setKey('name')
      key1DTO.setValue(mailerMap["body"]["name"])
        
      bodyMapDTO.getEntries().add(key1DTO)
      
      key2DTO = SmvBean::KeyValueEntryDTO.new
      key2DTO.setKey(mailerMap["body"]["key"])
      key2DTO.setValue(mailerMap["body"]["url"])
        
      bodyMapDTO.getEntries().add(key2DTO)
      
      mailerDTO.setBodyData(bodyMapDTO)
      
      emailDTO = SmvBean::EmailNameListDTO.new
      key3DTO = SmvBean::EmailNameEntryDTO.new
      key3DTO.setName(mailerMap["name"])
      key3DTO.setEmail(mailerMap["email"])
      emailDTO.getEntries().add(key3DTO)

      mailerDTO.setTo(emailDTO)
      return mailerDTO
    end
   
    
    def ObjectConverter.getSmvHostName()
           return WebConfig::ResourceManager.getSmvHostName()
    end
    
    #def ObjectConverter.mapToUserOutletDTO(params)
      
    #  outletMap = params["outlet"]
    #  outletDTO  =  SmvBean::UserOutletDTO.new
    #  outletDTO.setUserId(outletMap["userId"])
      
    #  return  outletDTO      
    #end
          
   end    
end