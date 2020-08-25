<template>
<div id="doctorscheduletable">
    <!-- <div class="limiter"> -->
      <!-- <div class="container-table100"> -->
        <div class="wrap-table100">
          <div class="table100 ver1 m-b-10">
            <table data-vertable="ver1">
              <thead>
                <tr class="row100 head">
                  <th class="column100 column1" data-column="column1">ID</th>
                  <th class="column100 column3" data-column="column3">Date</th>
                  <th class="column100 column4" data-column="column4">From</th>
                  <th class="column100 column5" data-column="column5">To</th>
                  <th class="column100 column7" data-column="column7"></th>
                </tr>
              </thead>
              <tbody>
                <tr class="row100" v-for="(schedule, index) in schedules" :key="index">
                  <td class="column100 column1" data-column="column1">{{index}}</td>
                  <td class="column100 column3" data-column="column3">{{schedule.date}}</td>
                  <td class="column100 column4" data-column="column4">{{schedule.from_time}}</td>
                  <td class="column100 column5" data-column="column5">{{schedule.to_time}}</td>
                  <button type="button" class="cancel_button" v-on:click="cancel_schedule(schedule.scheduleID)">Cancel</button>
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
  name: 'DoctorScheduleTable',
  data () {
    return {
      doctor_id: '',

      schedules: {}
    }
  },
  beforeCreate: function () {
    document.body.classList.add('doctorscheduletable')
  },
  created () {
    this.doctor_id = this.$route.params.id
    this.get_schedules()
  },
  mounted: function () {
    this.$root.$on('updateDoctorScheduleTable', (text) => { // here you need to use the arrow function
      // console.log(text)
      this.get_schedules()
    })
  },
  methods: {
    get_schedules: async function () {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/doctor/' + this.doctor_id + '/schedules')
        .then(response => {
          // eslint-disable-next-line eqeqeq
          if (response.data != '') {
            this.schedules = response.data
            // console.log(response.data)
          } else {
            // console.log('null')
            this.schedules = response.data
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
    cancel_schedule: async function (scheduleid) {
      const currentObject = this
      await axios.delete('http://localhost:8080/Project/REST-API/doctors/' + this.doctor_id + '/deleteschedules/' + scheduleid)
        .then(response => {
          // console.log(response.data)
          // eslint-disable-next-line eqeqeq
          if (response.data == 'Schedule Deleted Successfully') {
            this.get_schedules()
            alert(response.data)
            this.$root.$emit('updateUserBookingTable', 'Schedule deleted')
            this.$root.$emit('updateDoctorScheduleTable', 'Schedule deleted')
            this.$root.$emit('updateDoctorBookingTable', 'Schedule deleted')
          } else {
            alert(response.data)
            this.get_schedules()
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
