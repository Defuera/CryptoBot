# We are Crypto Bot

We will update you on Bitcoin price if you ask us gently.

Bot API https://github.com/pengrad/java-telegram-bot-api

# Setup for development

## Add configs
```telegram/keystore.properties``` must contain following parametets:
- IS_DEBUG
- BOT_TOKEN
- BOT_NAME = MyBotNameBot, needed to cut out the name from /command@MyBotNameBot
- FEEDBACK_CHANNEL_ID = id of the channel you want to post users feedback

```core/keystore.properties```:
- MONGO_ADDRESS = mongo database host ip address
- MONGO_USER 
- MONGO_PASS 

## Run
* Install JDK 8 http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* run ```./gradlew run```

## Some other useful gradle commands
* ```./gradlew clean build --stacktrace``` - run tests and build with log
* ```./gradlew test``` - run unit tests
