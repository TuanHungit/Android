

/*
 * GEt WEATHER by current Location <<< iD  >>>
 * Example ``RESPONSE``:
 * URi: " https://www.metaweather.com/api/location/924938/ "
 *
  [
    {
      "consolidated_weather": [
      {
        "id": 5364180358004736,

        "weather_state_name": "Heavy Cloud", // << WEATHER

        "weather_state_abbr": "hc",
        "wind_direction_compass": "WSW",

        "created": "2018-08-21T06:09:14.113170Z", // << CREATED

        "applicable_date": "2018-08-21",
        "min_temp": 16.8,
        "max_temp": 27.63333333333333,

        "the_temp": 27.45, // << The TEMPERATURE

        "wind_speed": 4.406190471627032,
        "wind_direction": 246.7213559823457,
        "air_pressure": 1014.44,
        "humidity": 59,
        "visibility": 14.79546945836316,
        "predictability": 71
      },
      ...
  ]
 *
 */
import Axios from 'axios'
export const getWeather = async () => {
    const response = await Axios.get(
      `https://weather-predictionn.herokuapp.com/iot`
    );
    let { current_Tepm, localtion, next_Temp} = response.data.data;
  return {
   currentTemp: current_Tepm,
   Location:localtion,
   NextTemp: next_Temp
  };
};