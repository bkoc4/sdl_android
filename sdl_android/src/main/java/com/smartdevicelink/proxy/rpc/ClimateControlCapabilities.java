package com.smartdevicelink.proxy.rpc;

import android.support.annotation.NonNull;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.proxy.rpc.enums.DefrostZone;
import com.smartdevicelink.proxy.rpc.enums.VentilationMode;
import java.util.Hashtable;
import java.util.List;

/**
 * Contains information about a climate control module's capabilities.
 */

public class ClimateControlCapabilities extends RPCStruct{
    public static final String KEY_MODULE_NAME= "moduleName";
    public static final String KEY_FAN_SPEED_AVAILABLE= "fanSpeedAvailable";
    public static final String KEY_DESIRED_TEMPERATURE_AVAILABLE= "desiredTemperatureAvailable";
    public static final String KEY_AC_ENABLE_AVAILABLE= "acEnableAvailable";
    public static final String KEY_AC_MAX_ENABLE_AVAILABLE= "acMaxEnableAvailable";
    public static final String KEY_CIRCULATE_AIR_ENABLE_AVAILABLE= "circulateAirEnableAvailable";
    public static final String KEY_AUTO_MODE_ENABLE_AVAILABLE= "autoModeEnableAvailable";
    public static final String KEY_DUAL_MODE_ENABLE_AVAILABLE= "dualModeEnableAvailable";
    public static final String KEY_DEFROST_ZONE_AVAILABLE= "defrostZoneAvailable";
    public static final String KEY_DEFROST_ZONE= "defrostZone";
    public static final String KEY_VENTILATION_MODE_AVAILABLE= "ventilationModeAvailable";
    public static final String KEY_VENTILATION_MODE= "ventilationMode";

    public ClimateControlCapabilities() {
    }

    public ClimateControlCapabilities(Hashtable<String, Object> hash) {
        super(hash);
    }

    /**
     * Constructs a newly allocated ClimateControlCapabilities
	 *
     * @param moduleName The short friendly name of the climate control module. It should not be used to identify a module by mobile application.
     */
    public ClimateControlCapabilities(@NonNull String moduleName) {
        this();
        setModuleName(moduleName);
    }

    /**
     * Sets the moduleName portion of the ClimateControlCapabilities class
     *
     * @param moduleName The short friendly name of the climate control module. It should not be used to identify a module by mobile application.
     */
    public void setModuleName(@NonNull String moduleName) {
        setValue(KEY_MODULE_NAME, moduleName);
    }

    /**
     * Gets the moduleName portion of the ClimateControlCapabilities class
     *
     * @return String - Short friendly name of the climate control module.
     */
    public String getModuleName() {
        return getString(KEY_MODULE_NAME);
    }

    /**
     * Sets the fanSpeedAvailable portion of the ClimateControlCapabilities class
     *
     * @param fanSpeedAvailable
     * Availability of the control of fan speed.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public void setFanSpeedAvailable(Boolean fanSpeedAvailable) {
        setValue(KEY_FAN_SPEED_AVAILABLE, fanSpeedAvailable);
    }

    /**
     * Gets the fanSpeedAvailable portion of the ClimateControlCapabilities class
     *
     * @return Boolean - Availability of the control of fan speed.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public Boolean getFanSpeedAvailable() {
        return getBoolean(KEY_FAN_SPEED_AVAILABLE);
    }

    /**
     * Sets the desiredTemperatureAvailable portion of the ClimateControlCapabilities class
     *
     * @param desiredTemperatureAvailable
     * Availability of the control of desired temperature.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public void setDesiredTemperatureAvailable(Boolean desiredTemperatureAvailable) {
        setValue(KEY_DESIRED_TEMPERATURE_AVAILABLE, desiredTemperatureAvailable);
    }

    /**
     * Gets the desiredTemperatureAvailable portion of the ClimateControlCapabilities class
     *
     * @return Boolean - Availability of the control of desired temperature.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public Boolean getDesiredTemperatureAvailable() {
        return getBoolean(KEY_DESIRED_TEMPERATURE_AVAILABLE);
    }

    /**
     * Sets the acEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @param acEnableAvailable
     * Availability of the control of turn on/off AC.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public void setAcEnableAvailable(Boolean acEnableAvailable) {
        setValue(KEY_AC_ENABLE_AVAILABLE, acEnableAvailable);
    }

    /**
     * Gets the acEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @return Boolean - Availability of the control of turn on/off AC.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public Boolean getAcEnableAvailable() {
        return getBoolean(KEY_AC_ENABLE_AVAILABLE);
    }

    /**
     * Sets the acMaxEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @param acMaxEnableAvailable
     * Availability of the control of enable/disable air conditioning is ON on the maximum level.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public void setAcMaxEnableAvailable(Boolean acMaxEnableAvailable) {
        setValue(KEY_AC_MAX_ENABLE_AVAILABLE, acMaxEnableAvailable);
    }

    /**
     * Gets the acMaxEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @return Boolean - Availability of the control of enable/disable air conditioning is ON on the maximum level.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public Boolean getAcMaxEnableAvailable() {
        return getBoolean(KEY_AC_MAX_ENABLE_AVAILABLE);
    }

    /**
     * Sets the circulateAirEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @param circulateAirEnableAvailable
     * Availability of the control of enable/disable circulate Air mode.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public void setCirculateAirEnableAvailable(Boolean circulateAirEnableAvailable) {
        setValue(KEY_CIRCULATE_AIR_ENABLE_AVAILABLE, circulateAirEnableAvailable);
    }

    /**
     * Gets the circulateAirEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @return Boolean - Availability of the control of enable/disable circulate Air mode.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public Boolean getCirculateAirEnableAvailable() {
        return getBoolean(KEY_CIRCULATE_AIR_ENABLE_AVAILABLE);
    }

    /**
     * Sets the autoModeEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @param autoModeEnableAvailable
     * Availability of the control of enable/disable auto mode.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public void setAutoModeEnableAvailable(Boolean autoModeEnableAvailable) {
        setValue(KEY_AUTO_MODE_ENABLE_AVAILABLE, autoModeEnableAvailable);
    }

    /**
     * Gets the autoModeEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @return Boolean - Availability of the control of enable/disable auto mode.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public Boolean getAutoModeEnableAvailable() {
        return getBoolean(KEY_AUTO_MODE_ENABLE_AVAILABLE);
    }

    /**
     * Sets the dualModeEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @param dualModeEnableAvailable
     * Availability of the control of enable/disable dual mode.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public void setDualModeEnableAvailable(Boolean dualModeEnableAvailable) {
        setValue(KEY_DUAL_MODE_ENABLE_AVAILABLE, dualModeEnableAvailable);
    }

    /**
     * Gets the dualModeEnableAvailable portion of the ClimateControlCapabilities class
     *
     * @return Boolean - Availability of the control of enable/disable dual mode.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public Boolean getDualModeEnableAvailable() {
        return getBoolean(KEY_DUAL_MODE_ENABLE_AVAILABLE);
    }

    /**
     * Sets the defrostZoneAvailable portion of the ClimateControlCapabilities class
     *
     * @param defrostZoneAvailable
     * Availability of the control of defrost zones.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public void setDefrostZoneAvailable(Boolean defrostZoneAvailable) {
        setValue(KEY_DEFROST_ZONE_AVAILABLE, defrostZoneAvailable);
    }

    /**
     * Gets the defrostZoneAvailable portion of the ClimateControlCapabilities class
     *
     * @return Boolean - Availability of the control of defrost zones.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public Boolean getDefrostZoneAvailable() {
        return getBoolean(KEY_DEFROST_ZONE_AVAILABLE);
    }

    /**
     * Gets the List<DefrostZone> portion of the ClimateControlCapabilities class
     *
     * @return List<DefrostZone> -  A set of all defrost zones that are controllable.
     */
    public List<DefrostZone> getDefrostZone() {
        return (List<DefrostZone>) getObject(DefrostZone.class, KEY_DEFROST_ZONE);
    }

    /**
     * Sets the defrostZone portion of the ClimateControlCapabilities class
     *
     * @param defrostZone
     * A set of all defrost zones that are controllable.
     */
    public void setDefrostZone(List<DefrostZone> defrostZone) {
        setValue(KEY_DEFROST_ZONE, defrostZone);
    }

    /**
     * Sets the ventilationModeAvailable portion of the ClimateControlCapabilities class
     *
     * @param ventilationModeAvailable
     * Availability of the control of air ventilation mode.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public void setVentilationModeAvailable(Boolean ventilationModeAvailable) {
        setValue(KEY_VENTILATION_MODE_AVAILABLE, ventilationModeAvailable);
    }

    /**
     * Gets the ventilationModeAvailable portion of the ClimateControlCapabilities class
     *
     * @return Boolean - Availability of the control of air ventilation mode.
     * True: Available, False: Not Available, Not present: Not Available.
     */
    public Boolean getVentilationModeAvailable() {
        return getBoolean(KEY_VENTILATION_MODE_AVAILABLE);
    }

    /**
     * Gets the List<VentilationMode> portion of the ClimateControlCapabilities class
     *
     * @return List<VentilationMode> -  A set of all ventilation modes that are controllable.
     */
    public List<VentilationMode> getVentilationMode() {
        return (List<VentilationMode>) getObject(VentilationMode.class, KEY_VENTILATION_MODE);
    }

    /**
     * Sets the ventilationMode portion of the ClimateControlCapabilities class
     *
     * @param ventilationMode
     * A set of all ventilation modes that are controllable.
     */
    public void setVentilationMode(List<VentilationMode> ventilationMode) {
        setValue(KEY_VENTILATION_MODE, ventilationMode);
    }
}
