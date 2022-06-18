<template>
  <b-container>
    <b-row>
      <b-col cols="10"></b-col>
      <b-col cols="2">
        <b-button
          v-if="checkInterest"
          @click="deleteInterest"
          variant="complete"
        >
          <b-icon icon="suit-heart-fill" variant="danger" scale="1.5"></b-icon>
        </b-button>
        <b-button v-else @click="addInterest" variant="complete">
          <b-icon icon="suit-heart" variant="danger" scale="1.5"></b-icon>
        </b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="12">
        <h1 class="detail-title mt-1" align="center">
          {{ currentHouse.apartmentName }}
        </h1>
      </b-col>
    </b-row>
    <div class="mt-4"></div>
    <b-tabs class="mt-3" justified>
      <b-tab title="최근매매">
        <basic-info
          :currentHouse="currentHouse"
          :roadAddress="roadAddress"
        ></basic-info>
      </b-tab>
      <b-tab>
        <template #title>{{ tabTitle }}</template>
        <deal-info
          :currentHouse="currentHouse"
          :deals="deals"
          :dongDeals="dongDeals"
        ></deal-info>
      </b-tab>
      <b-tab title="주변정보">
        <env-info :currentHouse="currentHouse"></env-info>
      </b-tab>
    </b-tabs>
  </b-container>
</template>

<script>
import BasicInfo from "@/components/map/detail/BasicInfo.vue";
import DealInfo from "@/components/map/detail/DealInfo.vue";
import EnvInfo from "@/components/map/detail/EnvInfo.vue";
import axios from "axios";
import { http } from "@/js/http.js";
import { mapState, mapActions, mapMutations } from "vuex";

const REST_API_KEY = process.env.VUE_APP_REST_API_KEY;

const envStore = "envStore";

export default {
  name: "SideInfoBar",
  components: {
    BasicInfo,
    DealInfo,
    EnvInfo,
  },

  props: {
    currentHouse: Object,
    userInterest: Array,
  },

  watch: {
    currentHouse: async function () {
      this.$emit("searchInterest");
      const response = await axios.get(
        `https://dapi.kakao.com/v2/local/geo/coord2address.json`,
        {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            x: this.currentHouse.lng,
            y: this.currentHouse.lat,
            input_coord: "WGS84",
          },
        }
      );
      this.roadAddress = response.data.documents[0].road_address.address_name;

      const response2 = await http.get("/map/searchdeal/latlng", {
        params: {
          lat: this.currentHouse.lat,
          lng: this.currentHouse.lng,
        },
      });
      this.deals = response2.data;
      this.tabTitle = "매매내역(" + this.deals.length + ")";

      const response3 = await http.get(
        `/map/searchdeal/${this.currentHouse.dongCode}`
      );

      this.dongDeals = response3.data;

      await this.set_subway({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_oil({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_kids({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_school({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_edu({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_food({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_convin({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_cafe({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_hospital({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_medi({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_mart({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });
      await this.set_bank({
        x: this.currentHouse.lng,
        y: this.currentHouse.lat,
      });

      if (this.isChange == false) {
        await this.SET_IS_CHANGE(true);
      } else {
        await this.SET_IS_CHANGE(false);
      }
    },

    userInterest: function (vals) {
      this.checkInterest = false;
      for (let val of vals) {
        if (val.aptCode == this.currentHouse.aptCode) {
          this.checkInterest = true;
        }
      }
    },
  },

  computed: {
    ...mapState(envStore, ["isChange"]),
  },

  data() {
    return {
      roadAddress: "",
      deals: [],
      tabTitle: "",
      dongDeals: [],
      checkInterest: false,
    };
  },

  mounted() {},

  methods: {
    ...mapMutations(envStore, ["SET_IS_CHANGE"]),
    ...mapActions(envStore, [
      "set_subway",
      "set_oil",
      "set_kids",
      "set_school",
      "set_edu",
      "set_food",
      "set_convin",
      "set_cafe",
      "set_hospital",
      "set_medi",
      "set_mart",
      "set_bank",
      "set_is_change",
    ]),

    addInterest() {
      if (this.userInterest.length >= 5) {
        alert("관심하우스는 최대 5개까지 등록할 수 있습니다.");
      } else {
        this.$emit("addInterest");
      }
    },

    deleteInterest() {
      this.$emit("deleteInterest");
    },
  },
};
</script>

<style>
a {
  font-size: 20px;
  color: #007bff;
  text-decoration: none;
  background-color: transparent;
}

.nav-tabs .nav-link.active,
.nav-tabs .nav-item.show .nav-link {
  color: #495057;
  background-color: #fff;
  border-color: #dee2e6 #dee2e6 #fff;
}

.detail-title {
  font-size: 45px;
}

::-webkit-scrollbar {
  display: none;
}
</style>
