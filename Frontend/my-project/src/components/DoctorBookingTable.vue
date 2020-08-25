<template>
<div id="doctorbookingtable">
    <!-- <div class="limiter"> -->
      <!-- <div class="container-table100"> -->
        <div class="wrap-table100">
          <div class="table100 ver1 m-b-10">
            <table data-vertable="ver1">
              <thead>
                <tr class="row100 head">
                  <th class="column100 column1" data-column="column1">ID</th>
                  <th class="column100 column2" data-column="column2">Patient Name</th>
                  <th class="column100 column3" data-column="column3">Date</th>
                  <th class="column100 column4" data-column="column4">Time</th>
                  <th class="column100 column5" data-column="column5">E-mail</th>
                  <th class="column100 column6" data-column="column6">Phone</th>
                  <th class="column100 column7" data-column="column7"></th>
                </tr>
              </thead>
              <tbody>
                <tr class="row100" v-for="(booking, index) in bookings" :key="index">
                  <td class="column100 column1" data-column="column1">{{index}}</td>
                  <td class="column100 column2" data-column="column2">{{booking.name}}</td>
                  <td class="column100 column3" data-column="column3">{{booking.date}}</td>
                  <td class="column100 column4" data-column="column4">{{booking.time}}</td>
                  <td class="column100 column5" data-column="column5">{{booking.email}}</td>
                  <td class="column100 column6" data-column="column6">{{booking.contact_no}}</td>
                  <button type="button" class="cancel_button" v-on:click="cancel_appointment(booking.bookingID)">Cancel</button>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      <!-- </div> -->
    <!-- </div> -->
</div>
</template>

<script>
// import router from '../router'
import axios from 'axios'

export default {
  name: 'DoctorBookingTable',
  data () {
    return {
      doctor_id: '',

      bookings: {}
    }
  },
  beforeCreate: function () {
    document.body.classList.add('doctorbookingtable')
  },
  created () {
    this.doctor_id = this.$route.params.id
    this.get_bookings()
  },
  mounted: function () {
    this.$root.$on('updateDoctorBookingTable', (text) => { // here you need to use the arrow function
      // console.log(text)
      this.get_bookings()
    })
  },
  methods: {
    get_bookings: async function () {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/doctor/' + this.doctor_id + '/bookings')
        .then(response => {
          // eslint-disable-next-line eqeqeq
          if (response.data != '') {
            this.bookings = response.data
            // console.log(response.data)
          } else {
            // console.log('null')
            this.bookings = response.data
          }
        })
        .catch(function (error) {
          currentObject.serverError = true
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.log(error.response.data)
            console.log(error.response.status)
            console.log(error.response.headers)
            currentObject.serverErrorMessage = error.response.data.message
          } else if (error.request) {
            // The request was made but no response was received
            console.log(error.request)
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message)
          }
          console.log(error.config)
        })
    },
    cancel_appointment: async function (bookingid) {
      const currentObject = this
      await axios.delete('http://localhost:8080/Project/REST-API/doctor/' + this.doctor_id + '/deletebooking/' + bookingid)
        .then(response => {
          // eslint-disable-next-line eqeqeq
          if (response.data == 'Booking Cancelled Successfully') {
            this.get_bookings()
            alert(response.data)
            this.$root.$emit('updateUserBookingTable', 'Booked Successful')
          } else {
            alert(response.data)
            this.get_bookings()
          }
        })
        .catch(function (error) {
          currentObject.serverError = true
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.log(error.response.data)
            console.log(error.response.status)
            console.log(error.response.headers)
            currentObject.serverErrorMessage = error.response.data.message
          } else if (error.request) {
            // The request was made but no response was received
            console.log(error.request)
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message)
          }
          console.log(error.config)
        })
    }
  }
}
</script>

<style scoped>
  /* Change cursor type */
  .cursor_type {
    cursor: pointer;
  }

  .cancel_button {
    margin-top: 14px;
    margin-bottom: 12px;
    display: inline-block;
    padding: 3px 20px;
    font-family: Montserrat-Regular;
    font-size: 14px;
    line-height: 1.4;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    outline: none;
    color: rgb(255, 255, 255);
    background-color: #e61212;
    border: none;
    border-radius: 15px;
    box-shadow: 0 5px #999;
  }

  .cancel_button:hover {background-color: #96141f}

  .cancel_button:active {
    background-color: #96141f;
    box-shadow: 0 3px #666;
    transform: translateY(2px);
  }
</style>

<style src="../../static/css/table.css" scoped>
</style>
