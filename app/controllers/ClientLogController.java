package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.LogMessage;
import play.Configuration;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;


public class ClientLogController extends Controller {

    @Inject
    Configuration configuration;

    @BodyParser.Of(BodyParser.Json.class)
    public Result receiveLogEntry() {

        Logger.trace("ClientLogController::receiveLogEntry() method");

        LogMessage clientLogMessage;

        JsonNode json = request().body().asJson();

        clientLogMessage = parseJsonLogEntry(json, request().remoteAddress());

        if(configuration.getBoolean("clientLogging.persistToDb")) {
            persistLogEntry(clientLogMessage);
        }

        if(configuration.getBoolean("clientLogging.outputToServerConsole")) {
            translateToLocalLogs(clientLogMessage);
        }

        return ok(Json.toJson(clientLogMessage));
    }

    private LogMessage parseJsonLogEntry(JsonNode jsonLog, String remoteAddress) {

        Logger.trace("ClientLogController::parseJsonLogEntry(JsonNode jsonLog) method");

        LogMessage logMessage = new LogMessage();

        logMessage.setTimestamp(new Date(jsonLog.findPath("t").asLong()));
        logMessage.setLogLevel(LogMessage.TranslateLogLevel(jsonLog.findPath("l").asInt()));
        logMessage.setRequestId(jsonLog.findPath("r").textValue());
        logMessage.setLogMessage(jsonLog.findPath("m").textValue());
        logMessage.setClientAddress(remoteAddress);

        return logMessage;

    }

    private void persistLogEntry(LogMessage logMessage) {

        Logger.trace("ClientLogController::persistJsonLogEntry(JsonNode jsonLog) method");

        logMessage.save();

    }

    private void translateToLocalLogs(LogMessage logMessage) {

        Logger.trace("ClientLogController::translateToLocalLogs(LogMessage logMessage) method");

        switch (logMessage.getLogLevel()) {
            case "DEBUG":
                Logger.debug(logMessage.toString());
                break;
            case "INFO":
                Logger.info(logMessage.toString());
                break;
            case "TRACE":
                Logger.trace(logMessage.toString());
                break;
            case "WARN":
                Logger.warn(logMessage.toString());
                break;
            case "ERROR":
                Logger.error(logMessage.toString());
                break;
            case "FATAL":
                Logger.error(logMessage.toString());
                break;
            default: break;

        }

    }

}
