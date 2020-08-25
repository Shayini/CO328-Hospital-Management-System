<template>
<div class="addschedule">
    <form>
        <h3 class="text-center">Add New Schedule</h3>
        <section>
            <span>Date :</span>
            <date-picker v-model="date" format="YYYY-MM-DD" type="date" :disabled-date="notBeforeToday" value-type="format" placeholder="Select date"></date-picker>
        </section>
        <section>
            <span>From :</span>
            <date-picker v-model="fromtime" format="HH:mm:ss" :show-second="false" :minute-step="15" :default-value="new Date().setHours(8, 0, 0, 0)" :disabled-time="notBeforeEightAndAfterEightOClock" value-type="format" type="time" placeholder="Select time"></date-picker>
        </section>
        <section>
            <span>To :</span>
            <date-picker v-model="totime" format="HH:mm:ss" :show-second="false" :minute-step="15" :default-value="new Date().setHours(8, 0, 0, 0)" :disabled-time="notBeforeEightAndAfterEightOClock" value-type="format" type="time" placeholder="Select time"></date-picker>
        </section>
        <button type="button" class="boxed-btn3" v-on:click="addSchedule()">Confirm</button>
    </form>
</div>
</template>

<script>
// import router from '../router'
import axios from 'axios'
import DatePicker from 'vue2-datepicker'
import 'vue2-datepicker/index.css'

export default {
  name: 'AddSchedule',
  components: {
    DatePicker
  },
  data () {
    return {
      doctor_id: '',
      fromtime: null,
      totime: null,
      date: null
    }
  },
  beforeCreate: function () {
    document.body.classList.add('addschedule')
  },
  created () {
    this.doctor_id = this.$route.params.id
  },
  methods: {
    addSchedule: async function () {
      console.log(this.doctor_id, this.fromtime, this.totime, this.date)
      if (this.fromtime && this.totime && this.date) {
        await axios.post('http://localhost:8080/Project/REST-API/doctor/' + this.doctor_id + '/addschedule', {
          from_time: this.fromtime,
          to_time: this.totime,
          date: this.date
        })
          .then(response => {
            // console.log(response.data)
            // eslint-disable-next-line eqeqeq
            if (response.data == 'Schedule Added Successful') {
              alert(response.data)
              this.$root.$emit('updateDoctorScheduleTable', 'Schedule Added Successful')
            } else {
              alert(response.data)
            }
          })
          .catch(function (error) {
            if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
              console.log(error.response.data)
              console.log(error.response.status)
              console.log(error.response.headers)
            //   currentObject.serverErrorMessage = error.response.data.message
            } else if (error.request) {
            // The request was made but no response was received
              console.log(error.request)
            } else {
            // Something happened in setting up the request that triggered an Error
              console.log('Error', error.message)
            }
            console.log(error.config)
          })
      } else {
        alert('Please fill the required fields')
      }
    },
    notBeforeToday: function (date) {
      const today = new Date()
      today.setHours(0, 0, 0, 0)
      return date.getTime() < today.getTime()
    },
    notBeforeEightAndAfterEightOClock: function (time) {
      return (time.getHours() < 8) || (time.getHours() > 20)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style src="../../static/css/addschedule.css" scoped></style>
