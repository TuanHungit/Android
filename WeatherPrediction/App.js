import React from 'react';
import {
  StyleSheet,
  View,
  ImageBackground,
  Text,
  KeyboardAvoidingView,
  Platform,
  ActivityIndicator,
  StatusBar,
} from 'react-native';

// Utils
import { getLocationId, getWeather } from './utils/api';
import getImageForWeather from './utils/getImageForWeather';
import getIconForWeather from './utils/getIconForWeather';

// Search component
import SearchInput from './SearchInput';

// MomentJS
import moment from 'moment';

// CLASS
export default class App extends React.Component {
  constructor(props) {
    super(props);

    // bind SCOPE
    this.handleDate = this.handleDate.bind(this);

    // STATE
    this.state = {
      loading: false,
      error: false,
      currentTemp: 0,
      Location:'',
      NextTemp:0
    };

  }
  // Life cycle
  componentDidMount() {
    this.handleUpdateLocation();
  }

  // Parse of date
  handleDate = date => moment(date).format("hh:mm:ss");

  // Update current location
  handleUpdateLocation = async () => {
   // if (!city) return;

    this.setState({ loading: true }, async () => {
      try {
        const {currentTemp,Location, NextTemp} = await getWeather();
        this.setState({
          loading: false,
          error: false,
          currentTemp,
          Location,
          NextTemp,
        });
      } catch (e) {
        this.setState({
          loading: false,
          error: true,
        });

      }
    });
  };

  // RENDERING
  render() {

    // GET values of state
     if (this.state.loaded) {
        this.handleUpdateLocation();
        }
    const { loading, error,currentTemp, Location,NextTemp } = this.state;

    // Activity
    return (

      <KeyboardAvoidingView style={styles.container} behavior="padding">

        <StatusBar barStyle="light-content" />

        <ImageBackground
          source={getImageForWeather('Clear')}
          style={styles.imageContainer}
          imageStyle={styles.image}
        >

          <View style={styles.detailsContainer}>
            <ActivityIndicator animating={loading} color="white" size="large" />
            {!loading && (
              <View>

                   <View>
                   <Text style={[styles.smallText, styles.textStyle]}>Địa Chỉ:</Text>
                        <Text style={[styles.large, styles.textStyle]}>
                                         {Location}
                        </Text>
                        <Text></Text><Text></Text><Text></Text><Text></Text>
                    </View>


                  <View>
                  <Text style={[styles.smallText, styles.textStyle]}>Nhiệt Độ hiện tại:</Text>
                    <Text style={[styles.largeText, styles.textStyle]}>
                      {`${(currentTemp)}°`}
                    </Text>
                    <Text></Text>
                  </View>
                 <View>
                  <Text style={[styles.smallText, styles.textStyle]}>Nhiệt Độ dự đoán sau 1 giờ:</Text>
                  <Text style={[styles.largeText, styles.textStyle]}>
                    {` ${(NextTemp)}°`}

                  </Text>
                  <Text></Text><Text></Text><Text></Text>
                  </View>

              </View>
            )}
          </View>
        </ImageBackground>
      </KeyboardAvoidingView>
    );
  }
}

/* StyleSheet */
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#34495E',
  },
  imageContainer: {
    flex: 1,
  },
  image: {
    flex: 1,
    width: null,
    height: null,
    resizeMode: 'cover',
  },
  detailsContainer: {
    flex: 1,
    justifyContent: 'center',
    backgroundColor: 'rgba(0,0,0,0.2)',
    paddingHorizontal: 20,
  },
  textStyle: {
    textAlign: 'center',
    fontFamily: Platform.OS === 'ios' ? 'AvenirNext-Regular' : 'Roboto',
    color: 'white',
  },
  largeText: {
    fontSize: 40,

  },
  smallText: {
    fontSize: 25,
  },
  large:{
  fontSize:30},
});
