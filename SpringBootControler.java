package com.example.aqs_new.api;
import com.example.aqs_new.Response.Response;
import com.example.aqs_new.admin.Admin;
import com.example.aqs_new.codeversion.Codeversion;
import com.example.aqs_new.device.Device;
import com.example.aqs_new.error.Error;
import com.example.aqs_new.errorlog.Errorlog;
import com.example.aqs_new.location.Location;
import com.example.aqs_new.partner.Partner;
import com.example.aqs_new.partnerpreviousaqi.Partnerpreviousaqi;
import com.example.aqs_new.partnerpreviouspm25.Partnerpreviouspm25;
import com.example.aqs_new.partnersignin.Partnersignin;
import com.example.aqs_new.publicside.Publicside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/", "aqs"})

public class AqsController {

    @Autowired
    AqsService aqsService;


    ////////////////////POST REQUEST METHODS/////////////////////////
    

    //Register Admin
    @RequestMapping(value = {"/admin"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetAdmin(@RequestBody Admin admin) {
        Response response = new Response();

        if (aqsService.adminEmailExist(admin.getEmail()))
        {
            response.setResponse("YOU CAN NOT BE AN ADMIN");
        }

        else 
	{
            aqsService.setAdmin(admin);
            response.setResponse("ADMIN REGISTERED");
        }
        return response;
    }

	
    //Register Partner
    @RequestMapping(value = {"/partner"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetPartner(@RequestBody Partner partner) {
        Response response = new Response();
        if (aqsService.partnerEmailExist(partner.getEmail()))
        {
            response.setResponse("ENTER OTHER EMAIL");
        }
        else 
	{
            aqsService.setPartner(partner);
            response.setResponse("Partner Registered");
        }
        return response;
    }


    //Signin Partner
    @RequestMapping(value = {"/partnersignin"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetPartnersignin(@RequestBody Partner partner)
    {
        Response response = new Response();
        if (aqsService.partnerPasswordEmailMatchCorrectly(partner))
        {
            Partnersignin partnersignin=new Partnersignin();
            Long partnerId=aqsService.partnetIdFinder(partner);
            partnersignin.setId(partnerId);
            aqsService.setPartnerSignin(partnersignin);
            response.setResponse("LOGGED IN");
        }
        else
        {
            response.setResponse("INVALID EMAIL OR PASSWORD");
        }
        return response;
    }

	
    //Register a new device
    @RequestMapping(value = {"/device"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetDevice(@RequestBody Device device) {

        Response response = new Response();

        if (aqsService.deviceIdExist(device.getId()))
        {
            response.setResponse("DEVICE ID ALREADY EXIST");
        }
        else if (aqsService.codeVersionNotExist(device.getCodeVersionId()))
        {
            response.setResponse("INVALID CODE VERSION");
        }
        else
        {
            aqsService.setDevice(device);
            response.setResponse("DEVICE REGISTERED");
        }
        return response;
    }


    //Add a new error
    @RequestMapping(value = {"/error"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetError(@RequestBody Error error)
    {
        Response response = new Response();
        aqsService.setError(error);
        response.setResponse("ERROR REGISTERED");
        return response;
    }


    //Add a new codeversion
    @RequestMapping(value = {"/codeversion"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetCodeversion(@RequestBody Codeversion codeversion) {
        Response response = new Response();

        if (aqsService.versionIdExist(codeversion.getId()))
        {
            response.setResponse("VERSION ID ALREADY EXIST");
        }
        else
        {
            response.setResponse("CODE VERSION REGISTERED");
            aqsService.setCodeversion(codeversion);
        }
        return response;
    }


    //Add a new Location
    @RequestMapping(value = {"/device/location"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetLocation(@RequestBody Location location) {
        Response response = new Response();

        Long deviceid=location.getDeviceId();
        if(deviceid!=null)
            aqsService.addNewLocation(deviceid);

        if (aqsService.deviceIdNotExist(location.getDeviceId()))
        {
            response.setResponse("DEVICE ID NOT EXIST");
        }
        else
        {
            //aqsService.deviceExistInPublicside(location); 
			// to update location of device in public side table
            aqsService.setLocation(location);
            aqsService.setPublicSideLocation(location);
            response.setResponse("DEVICE LOCATION STORED");

        }
        return response;
    }

	
    //Add a new Errorlog
    @RequestMapping(value = {"device/errorlog"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetErrorlog(@RequestBody Errorlog errorlog) {
        Response response = new Response();

        if (aqsService.deviceIdNotExist(errorlog.getDeviceId()))
        {
            response.setResponse("DEVICE ID NOT EXIST");
        }
        else if (aqsService.errorIdNotExist(errorlog.getErrorId()))
        {
            response.setResponse("ERROR ID NOT EXIST");
        }
        else
        {
        aqsService.setErrorlog(errorlog);
        response.setResponse("ERRORLOG RECORDED");
        }
        return response;
    }


    //Add new packet records
    @RequestMapping(value = {"device/datarecord"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetDatarecord(@RequestBody DataRecord datarecord) {
        Response response = new Response();
        if (aqsService.deviceIdNotExist(datarecord.getDeviceId()))
        {
            response.setResponse("DEVICE ID NOT EXIST");
        }
        else
        {
            aqsService.setDataRecord(datarecord);
            aqsService.updatePublicsiePartnerPreviousPm25Aqi(datarecord);
            aqsService.setParameterTable(datarecord);
            response.setResponse("DATA SAVED");
        }
        return response;
    }
	

    /////////////////////GET REQUEST METHODS/////////////////////////
    

    //Retrieve errorlogs for a device
    @RequestMapping(value = {"device/errorlog/{deviceid}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Errorlog> GetDeviceErrorlogs(@PathVariable("deviceid") Long deviceid)
    {
        return aqsService.getErrorlog(deviceid);
    }

	
    //Retrieve all Publicside records
    @RequestMapping(value = {"/publicside"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Publicside> GetPublicside() {

        return aqsService.getAllPublicside();

    }


    //Get AQI and Closest location of a website user
    @RequestMapping(value = {"/publicside/{latitude}/{longitude}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Publicside> GetHomeData(@PathVariable("latitude") double latitude,@PathVariable("longitude") double longitude) 
	{
        return aqsService.getHomeData(latitude,longitude);
    }

	
    //Retrieve all records by Aqi
    @RequestMapping(value = {"/device/datarecord/{aqi}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<DataRecord> GetDataRecordByAqi(@PathVariable("aqi") Long aqi)
    {
        return aqsService.FindDataRecordByAqi(aqi);
    }

	
    //Retrieve all records by Pm25
    @RequestMapping(value = {"/device/datarecord/{Pm25}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<DataRecord> GetDataRecordByPm25(@PathVariable("Pm25") Double Pm25)
    {
        return aqsService.FindDataRecordByPM25(Pm25);
    }

	
    //Retrieve all records by Temperature
    @RequestMapping(value = {"/device/datarecord/{temperaure}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<DataRecord> GetDataRecordByTemperature(@PathVariable("Temperature") Long Temperature)
    {
        return aqsService.FindDataRecordByAqi(Temperature);
    }

	
    //Retrieve all records by Humidity
    @RequestMapping(value = {"/device/datarecord/{Humidity}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<DataRecord> GetDataRecordByHumidity(@PathVariable("Humidity") Long Humidity)
    {
        return aqsService.FindDataRecordByAqi(Humidity);
    }

	
    //Retrieve all prvious AQI values of a device
    @RequestMapping(value = {"/device/partnerpreviousaqi/{deviceid}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Partnerpreviousaqi> GetPartnerpreviousaqi(@PathVariable("deviceid") Long deviceid)
    {
        return aqsService.findAllPartnerPreviousAqi(deviceid);
    }

	
    //Retrieve all prvious PM2.5 values of a device
    @RequestMapping(value = {"/device/partnerpreviouspm25/{deviceid}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Partnerpreviouspm25> GetPartnerpreviouspm25(@PathVariable("deviceid") Long deviceid)
    {
        return aqsService.findAllPartnerPreviousPm25(deviceid);
    }

	
    //Retrieve locations of all devices
    @RequestMapping(value = {"/location"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Location> GetLocations()
    {
        return aqsService.getlocations();
    }
    
	
    //Check for firmware upgrade
    @RequestMapping(value = {"/device/{id}/{sensor_combination_code}/{code_version}"}, method = RequestMethod.GET)
    public @ResponseBody
    Response CheckCodeVersionUpgradeAvailabilty(@PathVariable("id") Long id, @PathVariable("sensor_combination_code") Long sensor_combination_code, @PathVariable("code_version") Long code_version)
    {
        return aqsService.CheckForUpgrade(id, sensor_combination_code, code_version);
    }

    
    //Find list of Devices
    @RequestMapping(value = {"/device"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Device> FindListOfDevices()
    {
        return aqsService.findDeviceList();
    }


    /////////////////////PUT REQUEST METHODS/////////////////////////
    

    //firmware upgrade Confirmation Acknowledgement
    @RequestMapping(value = {"/device/{id}/{sensor_combination_code}/{code_version}"}, method = RequestMethod.PUT)
    public @ResponseBody
    Response VersionUpgradeRecord(@PathVariable("id") Long id, @PathVariable("sensor_combination_code") Long sensor_combination_code, @PathVariable("code_version") Long code_version)
    {
        return aqsService.UpgradeDeviceRecord(id, sensor_combination_code, code_version);
    }


    /////////////////////DELETE REQUEST METHODS//////////////////////
    

    //Update Location
    @RequestMapping(value = {"location"}, method = RequestMethod.DELETE)
    public @ResponseBody
    void UpdateLocation(Location location)
    {
        //Response response = aqsService.deleteDevice(deviceid);
        Long deviceid=location.getDeviceId();
        if(deviceid!=null)
        location.addNewLocation(deviceid);

    }


}
