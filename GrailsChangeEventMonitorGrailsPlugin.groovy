import org.codehaus.groovy.grails.plugins.PluginManagerHolder
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class GrailsChangeEventMonitorGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Your name"
    def authorEmail = ""
    def title = "Plugin summary/headline"
    def description = '''\\
Brief description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/divideby0/grails-change-event-monitor"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
		def disableScanning = ConfigurationHolder.config.changeEventMonitor?.disableScanning
	
		if(disableScanning) {
			manager.metaClass.startPluginChangeScanner = { ->
				log.info "Ignoring request to start plugin change scanner as it is disabled."
			}
		}
		
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
		log.info "event: ${event.manager}"
        log.info "resource changed: ${event.source}"
    }

    def onConfigChange = { event ->
		log.info "event: ${event}"
        log.info "config changed: ${event.source}"
    }
}
