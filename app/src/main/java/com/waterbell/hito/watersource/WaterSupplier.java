package com.waterbell.hito.watersource;

/**
 * Created by hito on 30-01-2015.
 */
public class WaterSupplier {

    int _id;
    String _name;
    String _location;
    String _city;
    String _state;
    String _emailId;
    String _contactNumber;
    String _address;

    public WaterSupplier(){
    }

    public WaterSupplier(int id, String name, String location, String city, String state, String emailId, String contactNumber, String address){
        this._id = id;
        this._name = name;
        this._location = location;
        this._city = city;
        this._state = state;
        this._emailId = emailId;
        this._contactNumber = contactNumber;
        this._address = address;
    }

    public WaterSupplier( String name, String location, String city, String state, String emailId, String contactNumber, String address){

        this._name = name;
        this._location = location;
        this._city = city;
        this._state = state;
        this._emailId = emailId;
        this._contactNumber = contactNumber;
        this._address = address;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String get_emailId() {
        return _emailId;
    }

    public void set_emailId(String _emailId) {
        this._emailId = _emailId;
    }

    public String get_contactNumber() {
        return _contactNumber;
    }

    public void set_contactNumber(String _contactNumber) {
        this._contactNumber = _contactNumber;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }
}
