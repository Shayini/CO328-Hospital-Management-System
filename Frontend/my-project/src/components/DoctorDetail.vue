<template>
<div id="doctordetail">
  <div id="doctors">
        <!-- Main container start -->
        <div class="main-container">
            <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
                <a class="navbar-brand" href="/"><img src="../../static/img/logo.png"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" :href="'/admin/' + admin_id + '/home'">My Home</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="expert_doctors_area">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-6">
                        <div class="section_title mb-55 text-center">
                            <h3>Our Doctors</h3>
                            <!-- <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore.</p> -->
                        </div>
                    </div>
                </div>
                <div class="ftco-departments">
                    <div class="row">
                        <div class="col-md-12 nav-link-wrap">
                            <div class="nav nav-pills d-flex text-center" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                                <a class="nav-link ftco-animate active" id="v-pills-1-tab" data-toggle="pill" href="#neurologist" role="tab" aria-controls="v-pills-1" aria-selected="true" v-on:click="set_department('cardiology')">Cardiology</a>

                                <a class="nav-link ftco-animate" id="v-pills-2-tab" data-toggle="pill" href="#surgical" role="tab" aria-controls="v-pills-2" aria-selected="false" v-on:click="set_department('dental')">Dental</a>

                                <a class="nav-link ftco-animate" id="v-pills-3-tab" data-toggle="pill" href="#dental" role="tab" aria-controls="v-pills-3" aria-selected="false" v-on:click="set_department('dermatology')">Dermatology</a>

                                <a class="nav-link ftco-animate" id="v-pills-4-tab" data-toggle="pill" href="#ophthalmology" role="tab" aria-controls="v-pills-4" aria-selected="false" v-on:click="set_department('neurology')">Neurology</a>

                                <a class="nav-link ftco-animate" id="v-pills-5-tab" data-toggle="pill" href="#cardiologist" role="tab" aria-controls="v-pills-5" aria-selected="false" v-on:click="set_department('ophthalmology')">Ophthalmology</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center" v-if="renderComponent">
                    <div v-for="(doct, index) in doctors" :key=index class="col-lg-3 col-md-3">
                        <div class="single_expert">
                            <div class="expert_thumb">
                                <img id="output" :src="urls[index]" alt="Doctor" />
                            </div>
                            <div class="experts_name text-center">
                                <h3>{{doct.doctorID}}</h3>
                                <span>{{doct.name}}</span>
                                <a class="nav-link cursor_type" v-on:click="get_doctor(doct.doctorID)" onclick="document.getElementById('id01').style.display='block'" style="width:auto;">More Detail</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal1" id="id01">
                    <form class="modal1-content animate">
                        <div class="imgcontainer">
                            <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                        </div>
                        <div class="containers">
                            <div class="side-bar">
                                <h5 class="tittle">Professional Details</h5>
                                <img class="img-responsive" id="output" :src="url" alt="Doctor" />
                                <ul class="personal-info">
                                    <li v-for="(value, name, index) in selected_doctor" :key="index">
                                        <p>
                                            <span>{{datas[index]}}</span> {{value}}
                                        </p>
                                    </li>
                                </ul>
                                <!-- <div class="contact-form"> -->
                                    <button type="button" value="Delete" class="delete delete_button" id="btn_submit_1" v-on:click="deletedoctor(selected_doctorID)">Delete</button>
                                <!-- </div> -->
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import router from '../router'
import axios from 'axios'

export default {
  name: 'DoctorDetail',
  data () {
    return {
      admin_id: '',

      url: require('../../static/img/profile.png'),
      url2: require('../../static/img/profile.png'),

      selected_department: 'cardiology',
      doctors: '',
      images: '',
      urls: {},
      datas: ['name', 'age', 'location', 'department', 'specialization', 'phone', 'e-mail'],
      selected_doctorID: '',
      selected_doctor: {
        name: 'Not filled',
        age: 'Not filled',
        location: 'Not filled',
        department: 'Not filled',
        specialization: 'Not filled',
        phone: 'Not filled',
        email: 'Not filled'
      },

      renderComponent: true // Used to refresh a particular div
    }
  },
  beforeCreate: function () {
    document.body.classList.add('doctordetail')
  },
  created () {
    this.admin_id = this.$route.params.id
    this.isActive()
  },
  methods: {
    forceRerender () { // Refresh a particular div (initially set renderComponent: true, add v-if="renderComponent" to that particular div, call this.forceRerender() where you want)
      // Remove my-component from the DOM
      this.renderComponent = false
      this.$nextTick().then(() => {
        // Add the component back in
        this.renderComponent = true
      })
    },
    set_department: function (dept) {
      this.selected_department = dept
      this.get_data()
    },
    isActive: async function () {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/admin/' + this.admin_id)
        .then(response => {
          if (response.data.active === false) {
            router.push('/')
          } else {
            this.get_data()
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
    get_data: async function () {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/doctor/department/' + this.selected_department)
        .then(response => {
          this.doctors = response.data
          for (var x = 0; x < response.data.length; x++) {
            this.urls[x] = require('../../static/img/profile.png')
            this.getParticularImage(response.data[x].doctorID, x)
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
    getParticularImage: async function (id, x) {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/doctor/image/get/' + id)
        .then(response => {
          if (response.data !== '') {
            this.url = 'data:image/*;base64,' + response.data.pic_byte
            this.urls[x] = this.url
          } else {
            this.url = require('../../static/img/profile.png')
            this.urls[x] = this.url
          }
          this.forceRerender()
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
    get_doctor: async function (id) {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/doctor/' + id)
        .then(response => {
          // console.log(response.data)
          if (response.data.name != null) this.selected_doctor.name = response.data.name
          if (response.data.age != null) this.selected_doctor.age = response.data.age
          if (response.data.address != null) this.selected_doctor.location = response.data.address
          if (response.data.department != null) this.selected_doctor.department = response.data.department
          if (response.data.specialization != null) this.selected_doctor.specialization = response.data.specialization
          if (response.data.contact_no != null) this.selected_doctor.phone = response.data.contact_no
          if (response.data.email != null) this.selected_doctor.email = response.data.email
          this.selected_doctorID = response.data.doctorID
          this.getImage(id)
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
    getImage: async function (id) {
      const currentObject = this
      // console.log(id)
      await axios.get('http://localhost:8080/Project/REST-API/doctor/image/get/' + id)
        .then(response => {
          if (response.data !== '') {
            this.url = 'data:image/*;base64,' + response.data.pic_byte
          } else {
            this.url = require('../../static/img/profile.png')
          }
          this.forceRerender()
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
    deletedoctor: async function (doctorid) {
      await axios.delete('http://localhost:8080/Project/REST-API/deletedoctor/' + doctorid)
        .then(response => {
          // console.log(response)
          alert(response.data)
          document.getElementById('id01').style.display = 'none'
          this.get_data()
        })
        .catch(function (error) {
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.log(error.response.data)
            console.log(error.response.status)
            console.log(error.response.headers)
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

<style src="../../static/css/home.css" scoped>
</style>
<style src="../../static/css/department.css" scoped>
</style>
<style src="../../static/css/doctorhome.css" scoped>
</style>
<style src="../../static/css/doctordetail.css" scoped>
</style>
