package com.example.weatherapp.bean;

import java.util.List;

// NOTICE!! All names have to be exactly same as in API documentation

public class BeanWeatherInformation {
    /**
     * data : [{"rh":87.1,"pod":"n","lon":117.80736,"pres":1028.41,"timezone":"Asia/Shanghai","ob_time":"2020-11-26 20:26","country_code":"CN","clouds":100,"ts":1606422376,"solar_rad":0,"state_code":"03","city_name":"Haikou","wind_spd":4.11004,"wind_cdir_full":"north-northwest","wind_cdir":"NNW","slp":1031.15,"vis":24,"h_angle":-90,"sunset":"09:07","dni":0,"dewpt":5.2,"snow":0,"uv":0,"precip":3.15789,"wind_dir":343,"sunrise":"22:49","ghi":0,"dhi":0,"aqi":131,"lat":29.10682,"weather":{"icon":"r01n","code":500,"description":"Light rain"},"datetime":"2020-11-26:19","temp":7.2,"station":"ZSOF","elev_angle":-34.41,"app_temp":4}]
     * count : 1
     */

    private Integer count;
    private List<DataDTO> data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * rh : 87.1
         * pod : n
         * lon : 117.80736
         * pres : 1028.41
         * timezone : Asia/Shanghai
         * ob_time : 2020-11-26 20:26
         * country_code : CN
         * clouds : 100
         * ts : 1606422376
         * solar_rad : 0
         * state_code : 03
         * city_name : Haikou
         * wind_spd : 4.11004
         * wind_cdir_full : north-northwest
         * wind_cdir : NNW
         * slp : 1031.15
         * vis : 24
         * h_angle : -90
         * sunset : 09:07
         * dni : 0
         * dewpt : 5.2
         * snow : 0
         * uv : 0
         * precip : 3.15789
         * wind_dir : 343
         * sunrise : 22:49
         * ghi : 0
         * dhi : 0
         * aqi : 131
         * lat : 29.10682
         * weather : {"icon":"r01n","code":500,"description":"Light rain"}
         * datetime : 2020-11-26:19
         * temp : 7.2
         * station : ZSOF
         * elev_angle : -34.41
         * app_temp : 4
         */

        private Double rh;
        //private String pod;
        //private Double lon;
        //private Double pres;
        private String timezone;
        private String ob_time;
        //private String countryCode;
        //private Integer clouds;
        //private Integer ts;
        //private Integer solarRad;
        //private String stateCode;
        private String city_name;
        //private Double windSpd;
        private String wind_cdir_full;
        //private String windCdir;
        //private Double slp;
        private Integer vis;
        //private Integer hAngle;
        //private String sunset;
        //private Integer dni;
        //private Double dewpt;
        //private Integer snow;
        //private Integer uv;
        //private Double precip;
        //private Integer windDir;
        //private String sunrise;
        //private Integer ghi;
        //private Integer dhi;
        private Integer aqi;
        //private Double lat;
        private DataDTO.Weather weather;
        //private String datetime;
        private Double temp;
        //private String station;
        //private Double elevAngle;
        //private Integer appTemp;

        public String getCityName() {
            return city_name;
        }

        public void setCityName(String cityName) {
            this.city_name = cityName;
        }

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getOb_time() {
            return ob_time;
        }

        public void setOb_time(String ob_time) {
            this.ob_time = ob_time;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public Weather getWeather() {
            return weather;
        }

        public void setWeather(Weather weather) {
            this.weather = weather;
        }

        public String getWind_cdir_full() {
            return wind_cdir_full;
        }

        public void setWind_cdir_full(String wind_cdir_full) {
            this.wind_cdir_full = wind_cdir_full;
        }

        public Integer getVis() {
            return vis;
        }

        public void setVis(Integer vis) {
            this.vis = vis;
        }

        public Double getRh() {
            return rh;
        }

        public void setRh(Double rh) {
            this.rh = rh;
        }

        public Integer getAqi() {
            return aqi;
        }

        public void setAqi(Integer aqi) {
            this.aqi = aqi;
        }

        public static class Weather {
            /**
             * icon : r01n
             * code : 500
             * description : Light rain
             */

            private String icon;
            private Integer code;
            private String description;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Integer getCode() {
                return code;
            }

            public void setCode(Integer code) {
                this.code = code;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }



}
