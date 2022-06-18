<template>
  <div id="app">
    <div v-if="this.show">
      <nav-bar></nav-bar>
    </div>
    <transition name="fade" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
import navBar from "@/components/nav/NavBar.vue";
export default {
  name: "App",

  data() {
    return {
      show: false,
    };
  },

  computed: {
    islogin() {
      if (sessionStorage.length != 0) {
        return JSON.parse(sessionStorage.getItem("vuex")).memberStore.isLogin;
      }
      return false;
    },
  },

  created() {
    if (this.islogin) {
      this.show = true;
    }
  },

  watch: {
    $route(to) {
      if (
        !(
          to.name == "memberLogin" ||
          to.name == "memberRegister" ||
          to.name == "memberFindId" ||
          to.name == "memberFindPass"
        )
      ) {
        this.show = true;
      } else {
        this.show = false;
      }
    },
  },

  components: {
    navBar,
  },

  mounted() {},
};
</script>

<style>
.fade-enter {
  opacity: 0;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.1s ease-out;
}
.fade-leave-to {
  opacity: 0;
}

@font-face {
  font-family: "Happiness-Sans-Title";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2205@1.0/Happiness-Sans-Title.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}

* {
  font-size: 0.981em;
  font-family: "Happiness-Sans-Title";
}
</style>
