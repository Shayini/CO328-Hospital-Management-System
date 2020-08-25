<template>
<div class="booking">
    <form>
        <h3 class="text-center">Make an Appointment</h3>
        <select class="form-select wide" id="default-select" @change="getDoctors()" v-model.trim='booking.department' required>
            <option value="">Select Department</option>
            <option v-for="(dept) in departments" :key="dept">
            {{dept}}
            </option>
        </select>
        <select class="form-select wide" id="default-select" @change="getDate()" v-model.trim='booking.doctor' required>
            <option value="">Select Doctors</option>
            <option v-for="(doct) in doctors" :key="doct.id" v-bind:value="{ doctorID: doct.doctorID, name: doct.name }">
            {{doct.name}}
            </option>
        </select>
        <select class="form-select wide" id="default-select" @change="getTime()" v-model.trim='booking.date' required>
            <option value="">Select Date</option>
            <option v-for="(sch) in schedules" :key="sch.id">
            {{sch.date}} from {{sch.from_time}} to {{sch.to_time}}
            </option>
        </select>
        <select class="form-select wide" id="default-select" v-model.trim='booking.time' required>
            <option value="">Select Time</option>
            <option v-for="(time) in unbooked_times" :key="time">
            {{time}}
            </option>
        </select>
        <input type="text"  placeholder="Phone no." v-model.trim='booking.contact_no' />
        <input type="email"  placeholder="Email" v-model.trim='booking.email' />
        <button type="button" class="boxed-btn3" v-on:click="addAppointment()">Confirm</button>
    </form>
</div>
</template>

<script>
// import router from '../router'
import axios from 'axios'

export default {
  name: 'Booking',
  data () {
    return {
      user_id: '',
      departments: [],
      doctors: [],
      schedules: [],
      booked_times: [],
      unbooked_times: [],
      times: [],
      booking: {
        department: '',
        doctor: '',
        date: '',
        time: '',
        contact_no: '',
        email: '',
        sch_id: ''
      }
    }
  },
  beforeCreate: function () {
    document.body.classList.add('booking')
  },
  created () {
    this.user_id = this.$route.params.id
    this.getDepartments()
  },
  methods: {
    empty: function () {
      this.booking.department = ''
      this.booking.doctor = ''
      this.booking.date = ''
      this.booking.time = ''
      this.booking.contact_no = ''
      this.booking.email = ''
      this.doctors = []
      this.schedules = []
      this.booked_times = []
      this.unbooked_times = []
      this.times = []
    },
    getDepartments: async function () {
      await axios.get('http://localhost:8080/Project/REST-API/doctor/departments')
        .then(response => {
          // console.log(response.data)
          this.departments = response.data.filter(function (item, index, inputArray) { // Remove duplicate elements
            return inputArray.indexOf(item) === index
          })
        })
        .catch(function (error) {
          // currentObject.serverError = true
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
    },
    getDoctors: async function () {
      this.booking.doctor = ''
      this.booking.date = ''
      this.booking.time = ''
      this.doctors = []
      this.schedules = []
      this.booked_times = []
      this.unbooked_times = []
      this.times = []
      if (this.booking.department !== '') {
        await axios.get('http://localhost:8080/Project/REST-API/user/' + this.user_id + '/' + this.booking.department)
          .then(response => {
            // console.log(response.data)
            this.doctors = response.data
          })
          .catch(function (error) {
            // currentObject.serverError = true
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
      }
    },
    getDate: async function () {
      this.booking.date = ''
      this.booking.time = ''
      this.schedules = []
      if (this.booking.doctor.doctorID !== '') {
        await axios.get('http://localhost:8080/Project/REST-API/doctor/' + this.booking.doctor.doctorID + '/schedules')
          .then(response => {
            // console.log(response.data)
            const today = new Date()
            // console.log(today)
            for (var i = 0; i < response.data.length; i++) {
              if (response.data[i].date > today.toISOString().substring(0, 10)) {
                this.schedules.push(response.data[i])
              }
            }
          })
          .catch(function (error) {
            // currentObject.serverError = true
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
      }
    },
    splitTime: function (st, et) {
      var sh = Number(st.split(':')[0])
      var sm = Number(st.split(':')[1])
      var eh = Number(et.split(':')[0])
      var em = Number(et.split(':')[1])
      var i, j
      for (i = sh, j = sm; i <= eh;) {
        if (i === eh && j === em) { break }
        if (i < 10) {
          if (j === 0) {
            this.times.push('0' + i + ':00:00')
          } else {
            this.times.push('0' + i + ':' + j + ':00')
          }
        } else {
          if (j === 0) {
            this.times.push(i + ':' + '00:00')
          } else {
            this.times.push(i + ':' + j + ':00')
          }
        }
        j = j + 15
        if (j >= 60) {
          j = 0
          i = i + 1
        }
      }
      // console.log(this.times)
    },
    getTime: async function () {
      this.booking.time = ''
      this.times = []
      this.booked_times = []
      this.unbooked_times = []
      if (this.booking.doctor.doctorID !== '') {
        await axios.get('http://localhost:8080/Project/REST-API/user/' + this.user_id + '/' + this.booking.doctor.doctorID + '/' + this.booking.date.split(' ')[0])
          .then(response => {
            // console.log(response.data)
            for (var k = 0; k < response.data.length; k++) {
              this.booked_times.push(response.data[k].time)
            }
            // console.log(this.booked_times)
            this.booking.sch_id = this.schedules.filter(schedule => schedule.date === this.booking.date.split(' ')[0] && schedule.from_time === this.booking.date.split(' ')[2])[0].scheduleID
            // console.log(this.booking.sch_id)
            this.splitTime(this.booking.date.split(' ')[2], this.booking.date.split(' ')[4])
            for (var i = 0; i < this.times.length; i++) {
              var match = false
              for (var j = 0; j < this.booked_times.length; j++) {
                if (this.booked_times[j] === this.times[i]) {
                  match = true
                }
              }
              if (!match) {
                this.unbooked_times.push(this.times[i])
              }
            }
            // console.log(this.unbooked_times)
          })
          .catch(function (error) {
            // currentObject.serverError = true
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
      }
    },
    addAppointment: async function () {
      if (this.booking.department && this.booking.doctor.doctorID && this.booking.date && this.booking.time && this.booking.email) {
        await axios.post('http://localhost:8080/Project/REST-API/user/' + this.user_id + '/addbooking/' + this.booking.doctor.doctorID + '/' + this.booking.sch_id, {
          department: this.booking.department,
          date: this.booking.date.split(' ')[0],
          time: this.booking.time,
          contact_no: this.booking.contact_no,
          email: this.booking.email
        })
          .then(response => {
            // console.log(response.data)
            // eslint-disable-next-line eqeqeq
            if (response.data == 'Booked Successful') {
              this.empty()
              alert(response.data)
              this.$root.$emit('updateUserBookingTable', 'Booked Successful')
              this.$root.$emit('updateDoctorBookingTable', 'Booked Successful')
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
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="../../static/css/booking.css" scoped></style>
