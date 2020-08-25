[11:28 PM, 8/24/2020] Thacha Akka: <template>
<div class="home">
    <div id="home">
        <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="/">
                <img src="../../static/img/logo.png" />
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link cursor_type" href="#home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link cursor_type" href="#doctors">Doctors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link cursor_type" onclick="document.getElementById('id02').style.display='none';document.getElementById('id01').style.display='block'" style="width:auto;">Signin</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link cursor_type" onclick="document.getElementById('id01').style.display='none';document.getElementById('id02').style.display='block'" style="width:auto;">Signup</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link cursor_type" href="#contactus">Contact</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" data-interval="7000">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="carousel-item active" v-bind:style="{ 'background-image': `url(${require('../../static/img/slides/1.jpg')})` }">
                    <div class="abc text-center">
                        <h1>Welcome to Medi</h1>
                        <!-- <a class="btn btn-outline-light btn-lg" href="#course">Online Booking</a> -->
                    </div>
                </div>
                <div class="carousel-item" v-bind:style="{ 'background-image': `url(${require('../../static/img/slides/2.jpg')})` }">
                    <div class="abc text-center">
                        <h1>Welcome to Medi</h1>
                        <!-- <a class="btn btn-outline-light btn-lg" href="#course">Online Booking</a> -->
                    </div>
                </div>
                <div class="carousel-item" v-bind:style="{ 'background-image': `url(${require('../../static/img/slides/3.jpg')})` }">
                    <div class="abc text-center">
                        <h1>Welcome to Medi</h1>
                        <!-- <a class="btn btn-outline-light btn-lg" href="#course">Online Booking</a> -->
                    </div>
                </div>
            </div>

            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
            </a>
        </div>
    </div>

    <div class="modal1" id="id01">
        <form class="modal1-content animate">
            <div class="imgcontainer">
                <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
            </div>
            <div class="containers">
                <signin></signin>
            </div>
        </form>
    </div>

    <div class="modal1" id="id02">
        <form class="modal1-content animate">
            <div class="imgcontainer">
                <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
            </div>
            <div class="containers">
                <signup></signup>
            </div>
        </form>
    </div>

    <div id="doctors">
        <div class="expert_doctors_area">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-6">
                        <div class="section_title mb-55 text-center">
                            <h3>Our Doctors</h3>
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
                                <a class="nav-link cursor_type" v-on:click="get_doctor(doct.doctorID)" onclick="document.getElementById('id03').style.display='block'" style="width:auto;">More Detail</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal1" id="id03">
                    <form class="modal1-content animate">
                        <div class="imgcontainer">
                            <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
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
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div id="contactus">
        <footer class="footer">
            <div class="footer_top">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-4 col-md-6 col-lg-4">
                            <div class="footer_widget">
                                <div class="footer_logo">
                                    <a href="/">
                                        <img src="../../static/img/logo.png" alt />
                                    </a>
                                </div>
                                <p class="address_text">
                                    Our vision is to progress medical services and
                                    speciality offerings in response to changing customer needs and trends through health, healing, and comfort.
                                </p>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6 col-lg-4">
                            <div class="footer_widget">
                                <h3 class="footer_title">Our Departments</h3>
                                <ul class="links">
                                    <li>
                                        <a href="#">Cardiology</a>
                                    </li>
                                    <li>
                                        <a href="#">Scan</a>
                                    </li>
                                    <li>
                                        <a href="#">Dental</a>
                                    </li>
                                    <li>
                                        <a href="#">X-ray</a>
                                    </li>
                                    <li>
                                        <a href="#">Dermatology</a>
                                    </li>
                                    <li>
                                        <a href="#">ECG</a>
                                    </li>
                                    <li>
                                        <a href="#">Neurology</a>
                                    </li>
                                    <li>
                                        <a href="#">Blood Test</a>
                                    </li>
                                    <li>
                                        <a href="#">Ophthalmology</a>
                                    </li>
                                    <li>
                                        <a href="#">Pharmacy</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6 col-lg-4">
                            <div class="footer_widget">
                                <h3 class="footer_title">We’re Available</h3>
                                <ul class="meting_time">
                                    <li class="d-flex justify-content-between">
                                        <span>Monday - Friday</span>
                                        <span>8.00 - 18.00</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <span>Saturday</span>
                                        <span>8.00 - 18.00</span>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <span>Sunday</span>
                                        <span>8.00 - 13.00</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>
    <div id="team"></div>
    <div id="contact"></div>
</div>
</template>

<script>
// import router from '../router'
import axios from 'axios'
import Signin from '@/components/Signin'
import Signup from '@/components/Signup'

export default {
  name: 'Home',
  components: {
    Signin,
    Signup
  },
  data () {
    return {
      url: require('../../static/img/profile.png'),
      url2: require('../../static/img/profile.png'),

      selected_department: 'cardiology',
      doctors: '',
      images: '',
      urls: {},
      datas: ['name', 'age', 'location', 'department', 'specialization', 'phone', 'e-mail'],
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
    document.body.classList.add('home')
  },
  created () {
    this.get_data()
  },
  mounted: function () {
    this.$root.$on('Close signup tab', (text) => { // here you need to use the arrow function
      // console.log(text)
      document.getElementById('id02').style.display = 'none'
    })
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
          if (response.data.name != null) this.selected_doctor.name = response.data.name
          if (response.data.age != null) this.selected_doctor.age = response.data.age
          if (response.data.address != null) this.selected_doctor.location = response.data.address
          if (response.data.department != null) this.selected_doctor.department = response.data.department
          if (response.data.specialization != null) this.selected_doctor.specialization = response.data.specialization
          if (response.data.contact_no != null) this.selected_doctor.phone = response.data.contact_no
          if (response.data.email != null) this.selected_doctor.email = response.data.email
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
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="../../static/css/home.css" scoped>
</style>
<style src="../../static/css/department.css" scoped>
</style>
