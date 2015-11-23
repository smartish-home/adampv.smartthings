/**
 *  Copyright 2015 AdamV
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Assign Buttons to Wireless Wall controllers
 *
 *  TODO
 *  Allow rename of app
 *  Get Double clicks to work and add as feature
 *  Get better dimming frequency than 1Hz
 *
 *  Version 1.0
 *  Author: AdamV
 *  Date: 2015-11-22
 *  To set colour and level of lights on push/hold events, connect to a routine or use smart lighting instead
 */
 
definition(
    name: "Button controller with dimming for Z-wave.me, Popp, & Devolo Wireless Wall Switches",
    namespace: "AdamV",
    author: "AdamV",
    description: "Assign events to button pushes, hold start, whilst held, & hold end to swicthes and level switches.For Z-Wave.me Secure Wireless Wall controller (ZME_WALLC-S), Z-Wave.me Wall controller 2 (ZME_WALLC-2), Popp Wall C Forever, Devolo Wall Switch & Z-Wave.me Key Fob",
    category: "Convenience",
    iconUrl: "http://94.23.40.33/smartthings/assets/rocker.png",
	iconX2Url: "http://94.23.40.33/smartthings/assets/rocker.png",
	iconX3Url: "http://94.23.40.33/smartthings/assets/rocker.png",
)

preferences {
    page(name: "selectController")
    page(name: "configureButton1")
    page(name: "configureButton2")
    page(name: "configureButton3")
    page(name: "configureButton4")

}

def selectController() {
    dynamicPage(name: "selectController", title: "First, select your button device", nextPage: "configureButton1", uninstall: configured()) {
        section {
            input "buttonDevice", "capability.button", title: "Controller", multiple: false, required: true
        	}
    	section([mobileOnly:true]) {
			label title: "Assign a name", required: false
			}
            
            section(title: "More options", hidden: hideOptionsSection(), hideable: true) {

            def timeLabel = timeIntervalLabel()

            href "timeIntervalInput", title: "Only during a certain time", description: timeLabel ?: "Tap to set", state: timeLabel ? "complete" : null

            input "days", "enum", title: "Only on certain days of the week", multiple: true, required: false,
                options: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]

            input "modes", "mode", title: "Only when mode is", multiple: true, required: false
        }

    }
}

def configureButton1() {
    dynamicPage(name: "configureButton1", title: "Decide how to use the first button:",
        nextPage: "configureButton2", uninstall: configured()) {
        def phrases = location.helloHome?.getPhrases()*.label
            if (phrases) {
                section("Button Press") {
                    log.trace phrases
                    input "Device1press", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
                    input "Device1pressRoutine", "enum", title: "Routine(s) to trigger", required: false, options: phrases
                }
            }               
        section ("Long Hold (instead of whilst held)")  {
            input "Device1longholdSwitch", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
            input "Device1longholdDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device1longholdDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
            }
        section ("Whilst Held Pulse (every 1s whilst held)")  {
            input "Device1heldDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device1heldDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
            }
        section ("When a Long Hold or Whilst Held is Released")  {
            input "Device1ReleaseSwitch", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
            input "Device1ReleaseDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device1ReleaseDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
        	}
    }
}
def configureButton2() {
    dynamicPage(name: "configureButton2", title: "Set Up your second button here:",
        nextPage: "configureButton3", uninstall: configured()) {
        def phrases = location.helloHome?.getPhrases()*.label
            if (phrases) {
                section("Button Press") {
                    log.trace phrases
                    input "Device2press", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
                    input "Device2pressRoutine", "enum", title: "Routine(s) to trigger", required: false, options: phrases
                }
            }  
        section ("Long Hold (instead of whilst held)")  {
            input "Device2longholdSwitch", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
            input "Device2longholdDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device2longholdDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
            }
        section ("Whilst Held Pulse (every 1s whilst held)")  {
            input "Device2heldDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device2heldDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
            }
        section ("When a Long Hold or Whilst Held is Released")  {
            input "Device2ReleaseSwitch", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
            input "Device2ReleaseDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device2ReleaseDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
        }
    }
}

def configureButton3() {
    dynamicPage(name: "configureButton3", title: "Set Up your third button here:",
        nextPage: "configureButton4", uninstall: configured()) {
        def phrases = location.helloHome?.getPhrases()*.label
            if (phrases) {
                section("Button Press") {
                    log.trace phrases
                    input "Device3press", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
                    input "Device3pressRoutine", "enum", title: "Routine(s) to trigger", required: false, options: phrases
                }
            }  
        section ("Long Hold (instead of whilst held)")  {
            input "Device3longholdSwitch", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
            input "Device3longholdDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device3longholdDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
            }
        section ("Whilst Held Pulse (every 1s whilst held)")  {
            input "Device3heldDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device3heldDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
            }
        section ("When a Long Hold or Whilst Held is Released")  {
            input "Device3ReleaseSwitch", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
            input "Device3ReleaseDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device3ReleaseDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
        }
    }
}
def configureButton4() {
    dynamicPage(name: "configureButton4", title: "Set Up your fourth button here:",
        install: true, uninstall: true ) {
        def phrases = location.helloHome?.getPhrases()*.label
            if (phrases) {
                section("Button Press") {
                    log.trace phrases
                    input "Device4press", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
                    input "Device4pressRoutine", "enum", title: "Routine(s) to trigger", required: false, options: phrases
                }
            }  
        section ("Long Hold (instead of whilst held)")  {
            input "Device4longholdSwitch", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
            input "Device4longholdDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device4longholdDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
            }
        section ("Whilst Held Pulse (every 1s whilst held)")  {
            input "Device4heldDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device4heldDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
            }
        section ("When a Long Hold or Whilst Held is Released")  {
            input "Device4ReleaseSwitch", "capability.switch", title: "Device(s) to switch on/off", multiple: true, required: false
            input "Device4ReleaseDimUp", "capability.switchLevel", title: "Device(s) to Dim / Roll Up", multiple: true, required: false
            input "Device4ReleaseDimDown", "capability.switchLevel", title: "Device(s) to Dim / Roll Down", multiple: true, required: false
        }
    }
}


def installed() {
    initialize()
}

def updated() {
    unsubscribe()
    initialize()
}

def initialize() {
    subscribe(buttonDevice, "button", buttonEvent)
}

def configured() {
    return buttonDevice || buttonConfigured(1) || buttonConfigured(2) || buttonConfigured(3) || buttonConfigured(4)
}

def buttonConfigured(idx) {
    return settings["Device$idxholdStartDimUp"] ||
        settings["Device$idxholdStartDimUp"]

}

def buttonEvent(evt){
    if(allOk) {
        //log.debug(evt.data)
        
        String [] extra = evt.data.split( "," )
        String extrapayload = extra[ 0 ]  

        String [] sections = extrapayload.split( ":" )
    //    log.debug("sections: $sections")

        String payload = sections[ 1 ]
//      log.debug( "Command: $payload" )
        
        String payload2 = payload.replaceAll("[/}/g]","")
        Integer payload3 = payload2.toInteger()
        
        def buttonNumber = payload3 // why doesn't jsonData work? always returning [:]
        def value = evt.value
    //  log.debug "buttonEvent: $evt.name = $evt.value ($evt.data)"
    //  log.debug "button: $buttonNumber, value: $value"

        atomicState.startHoldTime = 0
        atomicState.buttonIsHolding = false
        atomicState.pulseDelay = 600
        atomicState.currentButton = -1
        executeHandlers(buttonNumber, value)

    }
}

def startPulsing() {
    log.debug ("pulse")
    def currentTime = now(); //milliseconds, please.    
    if( atomicState.currentButton == -1 ) { return; }       
    if( atomicState.buttonIsHolding == false ) {
        return
    }
    def button = atomicState.currentButton
    
    if( currentTime - atomicState.startHoldTime >= pulseDelay ) {
        sendButtonHoldContinue();
        }
        
    // Delay?!
    runIn( 1, startPulsing )
}

def sendButtonHoldContinue() {
    if( atomicState.currentButton == -1 ) { return; }
    if( atomicState.buttonIsHolding == false ) { return; }
    if( atomicState.direction == null ) { return; }
    
    if( atomicState.direction == "Up" && atomicState.currentButton == 1 ) {
        log.debug ("going up with 1")
        Device1heldDimUp.levelUp()
        }
    else if( atomicState.direction == "Down" && atomicState.currentButton == 1 ) {
        log.debug ("going down with 1")
        Device1heldDimDown.levelDown()
        } 
    else if( atomicState.direction == "Up" && atomicState.currentButton == 2 ) {
        log.debug ("going up with 2")
        Device2heldDimUp.levelUp()
        }
    else if( atomicState.direction == "Down" && atomicState.currentButton == 2 ) {
        log.debug ("going down with 2")
        Device2heldDimDown.levelDown()
        } 
    else if( atomicState.direction == "Up" && atomicState.currentButton == 3 ) {
        log.debug ("going up with 3")
        Device3heldDimUp.levelUp()
        }
    else if( atomicState.direction == "Down" && atomicState.currentButton == 3 ) {
        log.debug ("going down with 3")
        Device3heldDimDown.levelDown()
        } 
    else if( atomicState.direction == "Up" && atomicState.currentButton == 4 ) {
        log.debug ("going up with 4")
        Device4heldDimUp.levelUp()
        }
    else if( atomicState.direction == "Down" && atomicState.currentButton == 4 ) {
        log.debug ("going down with 4")
        Device4heldDimDown.levelDown()
        } 
    
}

def onButtonHoldStart() {
    atomicState.startHoldTime = now()
    atomicState.buttonIsHolding = true
    startPulsing()
}

def onButtonHoldEnd() {
    atomicState.buttonIsHolding = false;
    log.debug ("HoldRelease event sent")
    atomicState.currentButton = -1;
    atomicState.direction = null
    unschedule("startPulsing")
}
def executeHandlers(buttonNumber, value) {
    //log.debug "executeHandlers: $buttonNumber - $value"
            if (value == "pushed" && buttonNumber == 1) {           
                if (Device1press != null) toggle(Device1press)
                if (Device1pressRoutine != null) location.helloHome?.execute(settings.Device1pressRoutine)
                log.debug "$buttonNumber $value"
            }
            else if (value == "held" && buttonNumber == 1) {
                atomicState.currentButton = buttonNumber
                if (Device1longholdSwitch != null) toggle(Device1longholdSwitch)
                if (Device1longholdDimUp != null) {
                    Device1longholdDimUp.levelUp()
                log.debug "Button $buttonNumber long hold going up"
                }
                if (Device1longholdDimDown != null) {
                    Device1longholdDimDown.levelDown()
                log.debug "Button $buttonNumber long hold going down"
                }
                if (Device1heldDimUp != null) {
                    atomicState.direction = "Up"
                log.debug "Button $buttonNumber Hold Start to go up"
                }
                if (Device1heldDimDown != null) {
                    atomicState.direction = "Down"
                log.debug "Button $buttonNumber Hold Start to go down"
                }
                onButtonHoldStart()
                log.debug "$buttonNumber $value"
            }
            else if (value == "holdRelease" && buttonNumber == 1) {         
                onButtonHoldEnd()
                if (Device1ReleaseSwitch != null) toggle(Device1ReleaseSwitch)
                if (Device1ReleaseDimUp != null) Device1ReleaseDimUp.levelUp()
                if (Device1ReleaseDimDown != null) Device1ReleaseDimDown.levelDown()
                log.debug "$buttonNumber $value"
            }
            else if (value == "pushed" && buttonNumber == 2) {
                if (Device2press != null) toggle(Device2press)
                if (Device2pressRoutine != null) location.helloHome?.execute(settings.Device2pressRoutine)
                log.debug "$buttonNumber $value"
            }
            else if (value == "held" && buttonNumber == 2) {
                atomicState.currentButton = buttonNumber
                if (Device2longholdSwitch != null) toggle(Device2longholdSwitch)
                if (Device2longholdDimUp != null) {
                    Device2longholdDimUp.levelUp()
                log.debug "Button $buttonNumber long hold going up"
                }
                if (Device2longholdDimDown != null) {
                    Device2longholdDimDown.levelDown()
                log.debug "Button $buttonNumber long hold going down"
                }
                if (Device2heldDimUp != null) {
                    atomicState.direction = "Up"
                log.debug "Button $buttonNumber Hold Start to go up"
                }
                if (Device2heldDimDown != null) {
                    atomicState.direction = "Down"
                log.debug "Button $buttonNumber Hold Start to go down"
                }
                onButtonHoldStart()
                log.debug "$buttonNumber $value"
            }
            else if (value == "holdRelease" && buttonNumber == 2) {         
                onButtonHoldEnd()
                if (Device2ReleaseSwitch != null) toggle(Device2ReleaseSwitch)
                if (Device2ReleaseDimUp != null) Device2ReleaseDimUp.levelUp()
                if (Device2ReleaseDimDown != null) Device2ReleaseDimDown.levelDown()
                log.debug "$buttonNumber $value"
            }
            else if (value == "pushed" && buttonNumber == 3) {
                if (Device3press != null) toggle(Device3press)
                if (Device3pressRoutine != null) location.helloHome?.execute(settings.Device3pressRoutine)
                log.debug "$buttonNumber $value"
            }
            else if (value == "held" && buttonNumber == 3) {
                atomicState.currentButton = buttonNumber
                if (Device3longholdSwitch != null) toggle(Device3longholdSwitch)
                if (Device3longholdDimUp != null) {
                    Device3longholdDimUp.levelUp()
                log.debug "Button $buttonNumber long hold going up"
                }
                if (Device3longholdDimDown != null) {
                    Device3longholdDimDown.levelDown()
                log.debug "Button $buttonNumber long hold going down"
                }
                if (Device3heldDimUp != null) {
                    atomicState.direction = "Up"
                log.debug "Button $buttonNumber Hold Start to go up"
                }
                if (Device3heldDimDown != null) {
                    atomicState.direction = "Down"
                log.debug "Button $buttonNumber Hold Start to go down"
                }
                onButtonHoldStart()
                log.debug "$buttonNumber $value"
            }
            else if (value == "holdRelease" && buttonNumber == 3) {         
                onButtonHoldEnd()
                if (Device3ReleaseSwitch != null) toggle(Device3ReleaseSwitch)
                if (Device3ReleaseDimUp != null) Device3ReleaseDimUp.levelUp()
                if (Device3ReleaseDimDown != null) Device3ReleaseDimDown.levelDown()
                log.debug "$buttonNumber $value"
            }
            else if (value == "pushed" && buttonNumber == 4) {
                if (Device4press != null) toggle(Device4press)
                if (Device4pressRoutine != null) location.helloHome?.execute(settings.Device4pressRoutine)
                log.debug "$buttonNumber $value"
            }
            else if (value == "held" && buttonNumber == 4) {
                atomicState.currentButton = buttonNumber
                if (Device4longholdSwitch != null) toggle(Device4longholdSwitch)
                if (Device4longholdDimUp != null) {
                    Device4longholdDimUp.levelUp()
                log.debug "Button $buttonNumber long hold going up"
                }
                if (Device4longholdDimDown != null) {
                    Device4longholdDimDown.levelDown()
                log.debug "Button $buttonNumber long hold going down"
                }
                if (Device4heldDimUp != null) {
                    atomicState.direction = "Up"
                log.debug "Button $buttonNumber Hold Start to go up"
                }
                if (Device4heldDimDown != null) {
                    atomicState.direction = "Down"
                log.debug "Button $buttonNumber Hold Start to go down"
                }
                onButtonHoldStart()
                log.debug "$buttonNumber $value"
            }
            else if (value == "holdRelease" && buttonNumber == 4) {         
                onButtonHoldEnd()
                if (Device4ReleaseSwitch != null) toggle(Device4ReleaseSwitch)
                if (Device4ReleaseDimUp != null) Device4ReleaseDimUp.levelUp()
                if (Device4ReleaseDimDown != null) Device4ReleaseDimDown.levelDown()
                log.debug "$buttonNumber $value"
            }

}

def toggle(devices) {
    log.debug "toggle: $devices = ${devices*.currentValue('switch')}"

    if (devices*.currentValue('switch').contains('on')) {
        devices.off()
    }
    else if (devices*.currentValue('switch').contains('off')) {
        devices.on()
    }
    else if (devices*.currentValue('lock').contains('locked')) {
        devices.unlock()
    }
    else if (devices*.currentValue('alarm').contains('off')) {
        devices.siren()
    }
    else {
        devices.on()
    }
}
// execution filter methods
private getAllOk() {
    modeOk && daysOk && timeOk
}

private getModeOk() {
    def result = !modes || modes.contains(location.mode)
//  log.trace "modeOk = $result"
    result
}

private getDaysOk() {
    def result = true
    if (days) {
        def df = new java.text.SimpleDateFormat("EEEE")
        if (location.timeZone) {
            df.setTimeZone(location.timeZone)
        }
        else {
            df.setTimeZone(TimeZone.getTimeZone("America/New_York"))
        }
        def day = df.format(new Date())
        result = days.contains(day)
    }
//  log.trace "daysOk = $result"
    result
}

private getTimeOk() {
    def result = true
    if (starting && ending) {
        def currTime = now()
        def start = timeToday(starting).time
        def stop = timeToday(ending).time
        result = start < stop ? currTime >= start && currTime <= stop : currTime <= stop || currTime >= start
    }
//  log.trace "timeOk = $result"
    result
}

private hhmm(time, fmt = "h:mm a")
{
    def t = timeToday(time, location.timeZone)
    def f = new java.text.SimpleDateFormat(fmt)
    f.setTimeZone(location.timeZone ?: timeZone(time))
    f.format(t)
}

private hideOptionsSection() {
    (starting || ending || days || modes) ? false : true
}

private timeIntervalLabel() {
    (starting && ending) ? hhmm(starting) + "-" + hhmm(ending, "h:mm a z") : ""
}