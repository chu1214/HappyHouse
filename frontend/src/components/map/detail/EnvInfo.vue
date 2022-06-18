<template>
  <div class="mt-3">
    <b-card
      header-bg-variant="dark"
      header-tag="header"
      style="border-radius: 20px"
    >
      <template #header>
        <h3 class="mt-2" style="color: white" align="center">
          해피하우스 지수
          <b-button size="sm" variant="complemte" id="btnHappyHouse">
            <b-icon scale="1.5" icon="question-circle" variant="white"></b-icon>
          </b-button>
          <b-popover
            target="btnHappyHouse"
            placement="topright"
            triggers="hover focus"
            variant="warning"
          >
            <b>카테고리</b> 평균 (5점기준)
          </b-popover>
        </h3>
      </template>
      <b-card-body
        ><h2 align="center">
          <b-form-rating
            id="totalRating"
            variant="warning"
            no-border
            inline
            v-model="totalRate"
            readonly
            show-value
            size="lg"
          ></b-form-rating>
        </h2>
      </b-card-body>
    </b-card>
    <div class="mt-3 list-group-flush">
      <b-list-group-item flush>
        <b-row>
          <b-col sm="6" style="font-size: 23px" class="ml-3"
            >교통
            <b-button size="sm" variant="complemte" id="btnTraffic">
              <b-icon icon="question-circle-fill" variant="secondary"></b-icon>
            </b-button>
            <b-popover
              target="btnTraffic"
              placement="topright"
              triggers="hover focus"
              variant="warning"
            >
              지하철: 0.5km 이내<br />주유소: 1km 이내 (5점기준)
            </b-popover>
          </b-col>
          <b-col sm="3">
            <b-form-rating
              id="trafficRating"
              no-border
              inline
              v-model="trafficRate"
              readonly
              show-value
            ></b-form-rating>
          </b-col>
          <b-col align="right">
            <b-button v-b-toggle.traffic variant="complete"
              ><b-icon icon="chevron-down"></b-icon>
            </b-button>
          </b-col>
        </b-row>
        <b-collapse id="traffic" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3"> > 지하철 </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.subway
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="subway" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="subway"
                v-for="(item, index) in this.subway"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
        <b-collapse id="traffic" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3"> > 주유소 </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.oil
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="oil" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="oil"
                v-for="(item, index) in this.oil"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
      </b-list-group-item>
      <b-list-group-item flush>
        <b-row>
          <b-col sm="6" style="font-size: 23px" class="ml-3"
            >교육
            <b-button size="sm" variant="complemte" id="btnEducation">
              <b-icon icon="question-circle-fill" variant="secondary"></b-icon>
            </b-button>
            <b-popover
              target="btnEducation"
              placement="topright"
              triggers="hover focus"
              variant="warning"
            >
              항목 별 개수 총합 40개(5점기준)
            </b-popover>
          </b-col>
          <b-col sm="3">
            <b-form-rating
              id="educationRating"
              no-border
              inline
              v-model="educationRate"
              readonly
              show-value
            ></b-form-rating>
          </b-col>
          <b-col align="right">
            <b-button v-b-toggle.education variant="complete"
              ><b-icon icon="chevron-down"></b-icon>
            </b-button>
          </b-col>
        </b-row>
        <b-collapse id="education" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3">
                > 어린이집/유치원
              </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.kids
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="kids" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="kids"
                v-for="(item, index) in this.kids"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
        <b-collapse id="education" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3"> > 학교 </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.school
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="school" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="school"
                v-for="(item, index) in this.school"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
        <b-collapse id="education" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3"> > 학원 </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.edu
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="edu" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="edu"
                v-for="(item, index) in this.edu"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
      </b-list-group-item>
      <b-list-group-item>
        <b-row>
          <b-col sm="6" style="font-size: 23px" class="ml-3"
            >문화/편의
            <b-button size="sm" variant="complemte" id="btnCurture">
              <b-icon icon="question-circle-fill" variant="secondary"></b-icon>
            </b-button>
            <b-popover
              target="btnCurture"
              placement="topright"
              triggers="hover focus"
              variant="warning"
            >
              총합 개수 15개 기준<br />음식점: 0.5km 이내<br />편의점: 0.2km
              이내<br />카페: 0.2km 이내 (5점기준)
            </b-popover>
          </b-col>
          <b-col sm="3">
            <b-form-rating
              id="cultureRating"
              no-border
              inline
              v-model="cultureRate"
              readonly
              show-value
            ></b-form-rating>
          </b-col>
          <b-col align="right">
            <b-button v-b-toggle.culture variant="complete"
              ><b-icon icon="chevron-down"></b-icon>
            </b-button>
          </b-col>
        </b-row>
        <b-collapse id="culture" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3"> > 음식점 </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.food
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="food" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="food"
                v-for="(item, index) in this.food"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
        <b-collapse id="culture" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col sm="9" style="font-size: 20px" class="ml-3">
                > 편의점
              </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.convin
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="convin" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="convin"
                v-for="(item, index) in this.convin"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
        <b-collapse id="culture" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3"> > 카페 </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.cafe
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="cafe" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="cafe"
                v-for="(item, index) in this.cafe"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
      </b-list-group-item>
      <b-list-group-item>
        <b-row>
          <b-col sm="6" style="font-size: 23px" class="ml-3"
            >의료
            <b-button size="sm" variant="complemte" id="btnMedical">
              <b-icon icon="question-circle-fill" variant="secondary"></b-icon>
            </b-button>
            <b-popover
              target="btnMedical"
              placement="topright"
              triggers="hover focus"
              variant="warning"
            >
              총합 개수 15개 기준<br />병원: 1km 이내<br />약국: 1km 이내
              이내(5점기준)
            </b-popover>
          </b-col>
          <b-col sm="3">
            <b-form-rating
              id="medicalRating"
              no-border
              inline
              v-model="medicalRate"
              readonly
              show-value
            ></b-form-rating>
          </b-col>
          <b-col align="right">
            <b-button v-b-toggle.medical variant="complete"
              ><b-icon icon="chevron-down"></b-icon>
            </b-button>
          </b-col>
        </b-row>
        <b-collapse id="medical" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3"> > 병원 </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.hospital
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="hospital" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="hospital"
                v-for="(item, index) in this.hospital"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
        <b-collapse id="medical" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col sm="9" style="font-size: 20px" class="ml-3">
                > 약국
              </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.medi
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="medi" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                name="medi"
                v-for="(item, index) in this.medi"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
      </b-list-group-item>
      <b-list-group-item flush>
        <b-row>
          <b-col sm="6" style="font-size: 23px" class="ml-3"
            >쇼핑
            <b-button size="sm" variant="complemte" id="btnShop">
              <b-icon icon="question-circle-fill" variant="secondary"></b-icon>
            </b-button>
            <b-popover
              target="btnShop"
              placement="topright"
              triggers="hover focus"
              variant="warning"
            >
              총합 개수 5개 기준<br />대형마트: 2km 이내(5점기준)
            </b-popover>
          </b-col>
          <b-col sm="3">
            <b-form-rating
              id="shopRating"
              no-border
              inline
              v-model="shopRate"
              readonly
              show-value
            ></b-form-rating>
          </b-col>
          <b-col align="right">
            <b-button v-b-toggle.shop variant="complete"
              ><b-icon icon="chevron-down"></b-icon>
            </b-button>
          </b-col>
        </b-row>
        <b-collapse id="shop" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3"> > 대형마트 </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.mart
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="mart" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                v-for="(item, index) in this.mart"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
      </b-list-group-item>
      <b-list-group-item flush>
        <b-row>
          <b-col sm="6" style="font-size: 23px" class="ml-3 list-group-flush"
            >금융
            <b-button size="sm" variant="complemte" id="btnBank">
              <b-icon icon="question-circle-fill" variant="secondary"></b-icon>
            </b-button>
            <b-popover
              target="btnBank"
              placement="topright"
              triggers="hover focus"
              variant="warning"
            >
              총합 개수 15개 기준<br />은행: 1km 이내(5점기준)
            </b-popover></b-col
          >
          <b-col sm="3">
            <b-form-rating
              id="bankRating"
              no-border
              inline
              v-model="bankRate"
              readonly
              show-value
            ></b-form-rating>
          </b-col>
          <b-col align="right">
            <b-button v-b-toggle.bank variant="complete"
              ><b-icon icon="chevron-down"></b-icon>
            </b-button>
          </b-col>
        </b-row>
        <b-collapse id="bank" class="mt-2 list-group-flush">
          <b-list-group-item flush>
            <b-row>
              <b-col style="font-size: 20px" class="ml-3"> > 은행 </b-col>
              <b-col align="right">
                <b-button
                  v-b-toggle.bank_atm
                  size="sm"
                  class="mr-1"
                  variant="complete"
                  ><b-icon icon="chevron-down"></b-icon
                ></b-button>
              </b-col>
            </b-row>
            <b-collapse id="bank_atm" class="mt-2 ml-3 list-group-flush">
              <b-list-group-item
                flush
                v-for="(item, index) in this.bank"
                :key="index"
                button
                style="cursor: default, padding: 0px"
              >
                <b-row>
                  <b-col sm="9" style="font-size: 18px">{{
                    item.place_name
                  }}</b-col>
                  <b-col style="font-size: 15px"
                    >{{ (item.distance / 1000).toFixed(1) }}km</b-col
                  >
                </b-row>
              </b-list-group-item>
            </b-collapse>
          </b-list-group-item>
        </b-collapse>
      </b-list-group-item>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const envStore = "envStore";

export default {
  name: "EnvInfo",

  props: {
    currentHouse: Object,
  },

  data() {
    return {
      trafficRate: "",
      educationRate: "",
      cultureRate: "",
      medicalRate: "",
      shopRate: "",
      bankRate: "",
      totalRate: "",
    };
  },

  watch: {
    check_is_change() {
      this.setSubwayRate();
      this.setOilRate();
      this.setFoodRate();
      this.setConvinRate();
      this.setCafeRate();
      this.setHospitalRate();
      this.setMediRate();
      this.setMartRate();
      this.setBankAtmRate();

      if (Number(this.subwayRate + this.oilRate) >= 5) {
        this.trafficRate = 5;
      } else {
        this.trafficRate = Number(this.subwayRate + this.oilRate).toFixed(1);
      }

      if (
        Number(
          ((this.kids.length + this.school.length + this.edu.length) / 40) * 5
        ) >= 5
      ) {
        this.educationRate = 5;
      } else {
        this.educationRate = Number(
          ((this.kids.length + this.school.length + this.edu.length) / 40) * 5
        ).toFixed(1);
      }

      if (Number((this.foodRate + this.convinRate + this.cafeRate) / 3) >= 5) {
        this.cultureRate = 5;
      } else {
        this.cultureRate = Number(
          (this.foodRate + this.convinRate + this.cafeRate) / 3
        ).toFixed(1);
      }

      if (Number(((this.hospitalRate + this.mediRate) / 2) * 5) >= 5) {
        this.medicalRate = 5;
      } else {
        this.medicalRate = Number(
          ((this.hospitalRate + this.mediRate) / 2) * 5
        ).toFixed(1);
      }

      if (Number(this.martRate) >= 5) {
        this.shopRate = 5;
      } else {
        this.shopRate = Number(this.martRate).toFixed(1);
      }

      if (Number(this.bankAtmRate) >= 5) {
        this.bankRate = 5;
      } else {
        this.bankRate = Number(this.bankAtmRate).toFixed(1);
      }

      this.totalRate = Number(
        (Number(this.trafficRate) +
          Number(this.educationRate) +
          Number(this.cultureRate) +
          Number(this.medicalRate) +
          Number(this.shopRate) +
          Number(this.bankRate)) /
          6
      ).toFixed(1);
    },
  },

  computed: {
    ...mapState(envStore, [
      "subway",
      "oil",
      "kids",
      "school",
      "edu",
      "food",
      "convin",
      "cafe",
      "hospital",
      "medi",
      "mart",
      "bank",
    ]),

    check_is_change() {
      return this.$store.getters["envStore/checkIsChange"];
    },
  },

  mounted() {},

  methods: {
    setSubwayRate() {
      if (this.subway.length == 0) {
        this.subwayRate = 0;
      } else {
        let closestSubway = this.subway[0].distance;
        let calc = Number((500 / closestSubway) * 3.5);
        this.subwayRate = calc >= 3.5 ? 3.5 : calc;
      }
    },
    setOilRate() {
      if (this.oil.length == 0) {
        this.oilRate = 0;
      } else {
        let closestOil = this.oil[0].distance;
        let calc = Number((1000 / closestOil) * 1.5);
        this.oilRate = calc >= 1.5 ? 1.5 : calc;
      }
    },
    setFoodRate() {
      let foodNum = 0;
      for (let food of this.food) {
        if (food.distance < 500) foodNum += 1;
      }
      let calc = Number((foodNum / 15) * 5);
      this.foodRate = calc >= 5 ? 5 : calc;
    },
    setConvinRate() {
      if (this.convin.length == 0) {
        this.convinRate = 0;
      } else {
        let closestConvin = this.convin[0].distance;
        let calc = Number((200 / closestConvin) * 5);
        this.convinRate = calc >= 5 ? 5 : calc;
      }
    },
    setCafeRate() {
      if (this.cafe.length == 0) {
        this.cafeRate = 0;
      } else {
        let closestCafe = this.cafe[0].distance;
        let calc = Number((200 / closestCafe) * 5);
        this.cafeRate = calc >= 5 ? 5 : calc;
      }
    },
    setHospitalRate() {
      let hospitalNum = 0;
      for (let hospital of this.hospital) {
        if (hospital.distance < 1000) hospitalNum += 1;
      }
      let calc = Number((hospitalNum / 15) * 5);
      this.hospitalRate = calc >= 5 ? 5 : calc;
    },
    setMediRate() {
      let mediNum = 0;
      for (let medi of this.medi) {
        if (medi.distance < 1000) mediNum += 1;
      }
      let calc = Number((mediNum / 15) * 5);
      this.mediRate = calc >= 5 ? 5 : calc;
    },
    setMartRate() {
      let martNum = 0;
      for (let mart of this.mart) {
        if (mart.distance < 2000) martNum += 1;
      }
      let calc = Number((martNum / 5) * 5);
      this.martRate = calc >= 5 ? 5 : calc;
    },
    setBankAtmRate() {
      let bankNum = 0;
      for (let bank of this.bank) {
        if (bank.distance < 1000) bankNum += 1;
      }
      let calc = Number((bankNum / 15) * 5);
      this.bankAtmRate = calc >= 5 ? 5 : calc;
    },
  },
};
</script>

<style scoped>
.card-header,
.card-header:first-child {
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
}

span div {
  display: inline-block;
}

::-webkit-scrollbar {
  display: none;
}

hr {
  /* margin-top: 1rem; */
  /* margin-bottom: 1rem; */
  border: 0;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}
</style>
