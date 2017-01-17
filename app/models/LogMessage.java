package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blog_log_message")
public class LogMessage extends Model {

    public static Finder<Long, LogMessage> find = new Finder<Long, LogMessage>(LogMessage.class);
    @Id
    @GeneratedValue
    Long id;
    @Constraints.Required
    Date timestamp;
    @Constraints.Required
    String logLevel;
    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    String logMessage;
    @Constraints.Required
    String requestId;
    @Constraints.Required
    String clientAddress;

    public static String TranslateLogLevel(int logLevel) {

        String level;

        switch (logLevel) {
            case 0: level = "DEBUG"; break;
            case 1 : level = "INFO"; break;
            case 2 : level = "TRACE"; break;
            case 3 : level = "WARN"; break;
            case 4 : level = "ERROR" ; break;
            case 5 : level =  "FATAL" ; break;
            default: level = "UNSPECIFIED"; break;
        }

        return level;
    }

    @Override
    public String toString() {
        return "CLIENT LOG MESSAGE" + " " + this.timestamp.toString() + " " + this.logLevel + " " + this.logMessage + " " + this.requestId + " " + this.clientAddress;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }


}
