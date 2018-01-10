import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.LevelRemappingAppender
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter
import org.springframework.core.env.StandardEnvironment

import static SpringBootConfig.*
import static ch.qos.logback.classic.Level.*

/**
 * Spring Boot org/springframework/boot/logging/logback_test/defaults.xml -> groovy 버전
 */

_PROFILE="${env.activeProfiles}"
_CONSOLE_LOG_PATTERN= "%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(${env.getProperty("PID", "")}){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n%wex"
_FILE_LOG_PATTERN= "%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${env.getProperty("PID", "")} --- [%t] %-40.40logger{39} : %m%n%wex"
_FILE_LOG_DIR="./logs"
_FILE_MAX_HISTORY=60
_FILE_MAX_SIZE="100MB"


//==== [START] Spring Boot 설정 시작 ====//

conversionRule("clr", ColorConverter)
conversionRule("wex", WhitespaceThrowableProxyConverter)

appender("DEBUG_LEVEL_REMAPPER", LevelRemappingAppender) {
    destinationLogger = "org.springframework.boot"
}
logger("org.apache.catalina.startup.DigesterFactory", ERROR)
logger("org.apache.catalina.util.LifecycleBase", ERROR)
logger("org.apache.coyote.http11.Http11NioProtocol", WARN)
logger("org.apache.sshd.common.util.SecurityUtils", WARN)
logger("org.apache.tomcat.util.net.NioSelectorPool", WARN)
logger("org.crsh.plugin", WARN)
logger("org.crsh.ssh", WARN)
logger("org.eclipse.jetty.util.component.AbstractLifeCycle", ERROR)
logger("org.hibernate.validator.internal.util.Version", WARN)
logger("org.springframework.boot.actuate.autoconfigure.CrshAutoConfiguration", WARN)
logger("org.springframework.boot.actuate.endpoint.jmx", null, ["DEBUG_LEVEL_REMAPPER"], false)
logger("org.thymeleaf", null, ["DEBUG_LEVEL_REMAPPER"], false)

class SpringBootConfig {
    static env = new StandardEnvironment()
    static {
        println("LOG PROFILE = ${env.activeProfiles}")
    }
}

//==== [END] Spring Boot 설정 끝 ====//


private void createLocalConsoleAppender() {
    def STDOUT = 'STDOUT'

    PRODUCT = STDOUT
    USER_ERROR = STDOUT
    APP_ERROR = STDOUT
    SLOW_LOGIC = STDOUT
    SLOW_QUERY = STDOUT

    appender(STDOUT, ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = _CONSOLE_LOG_PATTERN
        }
    }
}

private void createServerAppenders() {
    createFileAppender(PRODUCT, _FILE_LOG_DIR, "product")
    createFileAppender(USER_ERROR, _FILE_LOG_DIR, "user-error")
    createFileAppender(APP_ERROR, _FILE_LOG_DIR, "app-error")
    createFileAppender(SLOW_LOGIC, _FILE_LOG_DIR, "slow-logic")
    createFileAppender(SLOW_QUERY, _FILE_LOG_DIR, "slow-query")
}

def createFileAppender(name, logDir, fileName) {
    appender(name, RollingFileAppender) {
        file = "${logDir}/${fileName}.log"
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${logDir}/backup/${fileName}-%d{yyyy-MM-dd}.%i.zip"
            maxHistory = _FILE_MAX_HISTORY
            timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
                maxFileSize = _FILE_MAX_SIZE
            }
        }
        encoder(PatternLayoutEncoder) {
            pattern = _FILE_LOG_PATTERN
        }
    }
}

/**
 * 로그 환경 설정
 */
ROOT_LEVEL = INFO
APP_LEVEL = DEBUG
TRACE_LEVEL = TRACE
QUERY_LEVEL = WARN
HIBERNATE_SQL_LEVEL = OFF

PRODUCT = 'PRODUCT'
USER_ERROR = 'USER_ERROR'
APP_ERROR = 'APP_ERROR'
SLOW_LOGIC = 'SLOW_LOGIC'
SLOW_QUERY = 'SLOW_QUERY'

switch (_PROFILE){
    case "production":
        createServerAppenders()
        ROOT_LEVEL = INFO
        APP_LEVEL = INFO
        TRACE_LEVEL = INFO
        QUERY_LEVEL = INFO
        HIBERNATE_SQL_LEVEL = OFF
        break;
    case "verify":
        createServerAppenders()
        QUERY_LEVEL = INFO
        HIBERNATE_SQL_LEVEL = DEBUG
        break;
    case "alpha":
        createServerAppenders()
        ROOT_LEVEL = DEBUG
        APP_LEVEL = DEBUG
        TRACE_LEVEL = DEBUG
        QUERY_LEVEL = INFO
        HIBERNATE_SQL_LEVEL = OFF
        break;
    case "development":
        createServerAppenders()
        QUERY_LEVEL = INFO
        HIBERNATE_SQL_LEVEL = DEBUG
        break;
    default: // local
//        createServerAppenders()
        createLocalConsoleAppender()
        QUERY_LEVEL = DEBUG
        HIBERNATE_SQL_LEVEL = DEBUG
        break;
}

/**
 * 로거 설정
 */
root(ROOT_LEVEL, [PRODUCT])

//운영 로그
logger("com.torres", APP_LEVEL, [PRODUCT], false)
logger("org.springframework.web.client", APP_LEVEL, [PRODUCT], false)

//예외 로그
logger("APP_ERROR", DEBUG, [APP_ERROR], false)
logger("USER_ERROR", DEBUG, [USER_ERROR], false)

//관리 로그
logger("TRACE", TRACE_LEVEL, [PRODUCT], false)
logger("SLOW_LOGIC", INFO, [SLOW_LOGIC], false)

logger("jdbc.sqlonly", QUERY_LEVEL, [PRODUCT], false)
logger("jdbc.sqltiming", WARN, [SLOW_QUERY], false)
logger("jdbc.audit", OFF, [PRODUCT], false)
logger("jdbc.resultset", OFF, [PRODUCT], false)
logger("jdbc.resultsettable", OFF, [PRODUCT], false)
logger("jdbc.connection", INFO, [PRODUCT], false)
logger("org.hibernate.SQL", HIBERNATE_SQL_LEVEL, [PRODUCT], false)
