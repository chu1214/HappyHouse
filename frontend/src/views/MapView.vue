<template>
  <div>
    <div>
      <search-bar
        @searchPlaces="searchPlaces"
        @makeShowFalse="makeShowFalse"
        @resultClick="resultClick"
        :searchResult="searchResult"
        :showResult="showResult"
        :userInterest="userInterest"
      ></search-bar>
    </div>
    <div
      class="position-absolute"
      style="z-index: 1000; bottom: 8.5px; right: 10px"
    >
      <b-button
        v-b-toggle.interestList
        id="interest"
        style="border-radius: 50%; width: 37px; height: 37px; padding: 7.5px"
        @click="clickOptions"
        ><b-icon icon="heart-fill" variant="danger"></b-icon
      ></b-button>
    </div>
    <b-collapse
      id="interestList"
      class="position-absolute"
      v-model="showInterest"
      style="z-index: 1000; bottom: 53px; right: 15px; width: 300px"
    >
      <div v-if="this.userInterest.length == 0">
        <b-list-group>
          <b-list-group-item
            style="
              width: 300px;
              height: 46px;
              background-color: white;
              border-radius: 10px;
            "
          >
            현재 관심하우스가 없습니다.
          </b-list-group-item>
        </b-list-group>
      </div>
      <div v-else>
        <b-list-group>
          <b-list-group-item
            id="interest-list-group"
            v-for="(item, index) in this.userInterest"
            :key="index"
            button
            style="pading: 5px"
          >
            <b-row>
              <b-col cols="9" style="font-size: 18px">
                <div
                  class="ml-2"
                  @click="moveToApt(item.lat, item.lng)"
                  style="width: 200px; height: 37px; line-height: 37px"
                >
                  {{ item.apartmentName }}
                </div>
              </b-col>
              <b-col cols="3" style="padding: 0px 20px" align="center">
                <b-button
                  @click="deleteInterest(true, item.aptCode)"
                  variant="complete"
                  style="padding: 5px"
                  ><b-icon icon="x"></b-icon
                ></b-button>
              </b-col>
            </b-row>
          </b-list-group-item>
        </b-list-group>
      </div>
    </b-collapse>
    <!-- 필터 시작 -->
    <div
      class="position-absolute"
      style="z-index: 1000; bottom: 8.5px; right: 55px"
    >
      <b-button
        v-b-toggle.filterList
        id="filters"
        @click="clickOptions"
        style="border-radius: 50%; width: 37px; height: 37px; padding: 7.5px"
        ><b-icon icon="sliders" variant="white"></b-icon
      ></b-button>
    </div>
    <b-collapse
      id="filterList"
      v-model="showFilter"
      class="position-absolute"
      style="z-index: 1000; bottom: 53px; right: 15px; width: 300px"
    >
      <div>
        <b-list-group
          class="list-group-flush"
          style="border-top-left-radius: 10px; border-top-right-radius: 10px"
        >
          <b-list-group-item
            style="width: 300px; height: 100px; background-color: white"
            ><b-row class="mt-2">
              <b-col cols="2">
                <b-icon
                  icon="piggy-bank"
                  scale="2"
                  variant="secondary"
                ></b-icon>
              </b-col>
              <b-col cols="4" style="padding: 0px; text-align: center">
                {{ costMinValue | price }}
              </b-col>
              <b-col cols="1" style="padding: 0px; text-align: center">
                -
              </b-col>
              <b-col cols="4" style="padding: 0px; text-align: center">
                {{ costMaxValue | price }}
              </b-col>
            </b-row>
            <MultiRangeSlider
              :baseClassName="`multi-range-slider`"
              :min="0"
              :max="1000000"
              :step="1000"
              :ruler="false"
              :label="false"
              :minValue="costMinValue"
              :maxValue="costMaxValue"
              @input="UpdateCost"
            />
          </b-list-group-item>
          <b-list-group-item
            style="width: 300px; height: 100px; background-color: white"
          >
            <b-row class="mt-2">
              <b-col cols="2">
                <b-icon
                  icon="house-door"
                  scale="2"
                  variant="secondary"
                ></b-icon>
              </b-col>
              <b-col cols="4" style="padding: 0px; text-align: center">
                {{ areaMinValue }} m<sup>2</sup>
              </b-col>
              <b-col cols="1" style="padding: 0px; text-align: center">
                -
              </b-col>
              <b-col cols="4" style="padding: 0px; text-align: center">
                {{ areaMaxValue }} m<sup>2</sup>
              </b-col>
            </b-row>
            <MultiRangeSlider
              :baseClassName="`multi-range-slider`"
              :min="0"
              :max="300"
              :step="1"
              :ruler="false"
              :label="false"
              :minValue="areaMinValue"
              :maxValue="areaMaxValue"
              @input="UpdateArea"
            />
          </b-list-group-item>
          <b-list-group-item
            style="
              width: 300px;
              height: 37px;
              background-color: white;
              padding: 0;
            "
          >
            <b-button block @click="clickSetting"
              ><b-icon icon="gear-fill"></b-icon> 적용</b-button
            >
          </b-list-group-item>
        </b-list-group>
      </div>
    </b-collapse>
    <!-- 필터 끝 -->
    <b-sidebar
      id="sideinfobar"
      width="550px"
      title=""
      right
      shadow
      v-model="isSideBarOpen"
      bg-variant="white"
    >
      <side-info-bar
        :currentHouse="currentHouse"
        :userInterest="userInterest"
        @searchInterest="callInterestList"
        @addInterest="addInterest"
        @deleteInterest="deleteInterest(false)"
      ></side-info-bar>
    </b-sidebar>
    <div id="map" class="map"></div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import SearchBar from "@/components/map/SearchBar.vue";
import SideInfoBar from "@/components/map/SideInfoBar.vue";
import { http } from "@/js/http.js";
import MultiRangeSlider from "multi-range-slider-vue";

const mapStore = "mapStore";
const memberStore = "memberStore";
const MAP_API_KEY = process.env.VUE_APP_MAP_API_KEY;

export default {
  name: "MapView",
  components: {
    SearchBar,
    SideInfoBar,
    MultiRangeSlider,
  },

  data() {
    return {
      map: null,
      markers: [],
      searchResult: [],
      showResult: false,
      clusterer: null,
      ps: null,
      customOverlays: [],
      isSideBarOpen: false,
      currentHouse: {},
      userInterest: [],
      interestInfo: {
        userid: "",
        aptCode: "",
        apartmentName: "",
        lat: "",
        lng: "",
      },
      saveSetting: false,
      showInterest: false,
      showFilter: false,
      costMinValue: 0,
      costMaxValue: 1000000,
      areaMinValue: 0,
      areaMaxValue: 300,
    };
  },

  async created() {
    await this.callInterestList();
  },

  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addMapScript();
  },

  computed: {
    ...mapState(mapStore, ["houseInfos"]),
    ...mapState(memberStore, ["userInfo"]),
  },

  watch: {
    houseInfos(n) {
      this.markInMap(n);
    },
    saveSetting() {
      this.tilesloaded();
    },
  },

  methods: {
    ...mapMutations(mapStore, ["SET_HOUSE_INFOS"]),
    addMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        MAP_API_KEY +
        "&libraries=clusterer,services";
      document.head.appendChild(script);
    },

    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.5665734, 126.978179), //지도의 중심좌표.
        level: 4, //지도의 레벨(확대, 축소 정도)
        maxLevel: 7,
      };
      this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체

      // 클러스터러
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 4, // 클러스터 할 최소 지도 레벨
      });

      // 타일로드 이벤트
      kakao.maps.event.addListener(this.map, "tilesloaded", this.tilesloaded);

      this.ps = new kakao.maps.services.Places();
    },

    async tilesloaded() {
      this.showResult = false;
      // 지도 영역정보를 얻어옵니다
      var bounds = this.map.getBounds();

      // 영역정보의 남서쪽 정보를 얻어옵니다
      var swLatlng = bounds.getSouthWest();

      // 영역정보의 북동쪽 정보를 얻어옵니다
      var neLatlng = bounds.getNorthEast();

      this.SET_HOUSE_INFOS({
        lat1: swLatlng.Ma,
        lng1: swLatlng.La,
        lat2: neLatlng.Ma,
        lng2: neLatlng.La,
      });
    },

    markInMap(updated) {
      this.clusterer.clear();
      this.clearMarkers(null);
      this.clearOverlays(null);

      var mapLevel = this.map.getLevel();
      if (mapLevel == 7) {
        return;
      } else if (mapLevel >= 4) {
        for (let house of updated) {
          var regex = /[^0-9]/g;
          var regex2 = /[^0-9.]/g;
          var result1 = house.dealAmount.replace(regex, "");
          var result2 = new Number(house.area.replace(regex2, ""));

          if (
            Number(result1) >= this.costMinValue &&
            Number(result1) <= this.costMaxValue &&
            result2 >= this.areaMinValue &&
            result2 <= this.areaMaxValue
          ) {
            var imageSrc = require("@/assets/marker.png");
            var imageSize = new kakao.maps.Size(42, 42);
            var imageOption = { offset: new kakao.maps.Point(27, 69) };
            var markerImage = new kakao.maps.MarkerImage(
              imageSrc,
              imageSize,
              imageOption
            );

            let position = new kakao.maps.LatLng(house.lat, house.lng);

            var marker = new kakao.maps.Marker({
              position: position,
              image: markerImage,
              clickable: true,
            });

            this.markers.push(marker);

            var zoomIn = this.zoomIn;
            kakao.maps.event.addListener(marker, "click", function () {
              zoomIn(position);
            });
          }
        }
        this.clusterer.addMarkers(this.markers);
      } else {
        for (let house of updated) {
          regex = /[^0-9]/g;
          regex2 = /[^0-9.]/g;
          result1 = house.dealAmount.replace(regex, "");
          result2 = new Number(house.area.replace(regex2, ""));
          if (
            Number(result1) >= this.costMinValue &&
            Number(result1) <= this.costMaxValue &&
            result2 >= this.areaMinValue &&
            result2 <= this.areaMaxValue
          ) {
            let content = document.createElement("div");
            var date =
              house.recentDate.substr(0, 4) +
              "." +
              house.recentDate.substr(4, 2) +
              "." +
              house.recentDate.substr(6, 2);
            content.className = "overlay";
            content.innerHTML = `
            <div class="custom-overlay-box">
              <div class="house-title">${house.apartmentName.substr(0, 8)}</div>
              <div class="deal-amount">${(result1 / 10000).toFixed(2)}억</div>
              <div>
                <span class="area">${result2.toFixed(1)}m<sup>2</sup> / </span>
                <span class="recent-date">${date}</span>
              </div>
            </div>
            `;

            let position = new kakao.maps.LatLng(house.lat, house.lng);
            var overlay = new kakao.maps.CustomOverlay({
              position: position,
              content: content,
              xAnchor: 0.3,
              yAnchor: 0.91,
              clickable: true,
            });

            var openSideBar = this.openSideBar;
            var transferHouseDetail = this.transferHouseDetail;
            this.addEventHandle(content, "click", function () {
              transferHouseDetail(house);
              openSideBar();
            });

            this.customOverlays.push(overlay);
          }
        }
        // 커스텀 오버레이를 지도에 표시합니다
        this.setOverlays(this.map);
      }
    },
    // 마커를 생성하고 지도위에 표시하는 함수입니다
    addMarker(map, position, data) {
      console.log(data);
      var imageSrc = require("@/assets/marker.png");
      var imageSize = new kakao.maps.Size(42, 42);
      var imageOption = { offset: new kakao.maps.Point(27, 69) };
      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        title: data.apartmentName,
        position: position,
        image: markerImage,
        clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      });
      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);
      // 생성된 마커를 배열에 추가합니다
      this.markers.push(marker);
    },
    // 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
    setMarkers(map) {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(map);
      }
    },
    clearMarkers() {
      this.setMarkers(null);
      this.markers = [];
    },

    setOverlays(map) {
      for (var i = 0; i < this.customOverlays.length; i++) {
        this.customOverlays[i].setMap(map);
      }
    },
    clearOverlays() {
      this.setOverlays(null);
      this.customOverlays = [];
    },

    searchPlaces(keyword) {
      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      this.ps.keywordSearch(keyword, this.placesSearchCB);
    },

    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        this.searchResult = data;
        console.log(data);
        this.showResult = true;
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        this.searchResult = [];
        this.showResult = false;
      } else if (status === kakao.maps.services.Status.ERROR) {
        this.showResult = false;
        alert("검색에 실패하였습니다.");
      }
    },

    makeShowFalse() {
      this.showResult = false;
    },

    resultClick(item) {
      const position = new kakao.maps.LatLng(item.y, item.x);
      this.map.setLevel(2);
      this.map.panTo(position);
    },

    zoomIn(position) {
      this.map.setLevel(2);
      this.map.panTo(position);
    },

    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },

    openSideBar() {
      this.isSideBarOpen = true;
    },

    transferHouseDetail(house) {
      this.currentHouse = house;
    },

    // 관심목록을 갱신하는 함수
    async callInterestList() {
      try {
        const response = await http.get("/user/interest/search", {
          params: {
            userid: this.userInfo.userid,
          },
        });
        if (response.data) {
          this.userInterest = response.data;
        }
      } catch (error) {
        alert("관심하우스 불러오기 실패");
      }
    },

    // 관심목록 등록시 실행되는 함수
    async addInterest() {
      this.interestInfo.userid = this.userInfo.userid;
      this.interestInfo.aptCode = this.currentHouse.aptCode;
      this.interestInfo.apartmentName = this.currentHouse.apartmentName;
      this.interestInfo.lat = this.currentHouse.lat;
      this.interestInfo.lng = this.currentHouse.lng;
      const response = await http.post(
        "/user/interest/regist",
        this.interestInfo
      );
      if (response.data == 1) {
        await this.callInterestList();
      } else {
        alert("관심하우스 등록 실패");
      }
    },

    // 관심목록 삭제시 실행되는 함수
    async deleteInterest(check, item) {
      if (check) {
        const conf = confirm("관심하우스를 삭제하시겠습니까?");
        if (!conf) {
          return;
        } else {
          this.interestInfo.userid = this.userInfo.userid;
          this.interestInfo.aptCode = item;
        }
      } else {
        this.interestInfo.userid = this.userInfo.userid;
        this.interestInfo.aptCode = this.currentHouse.aptCode;
      }

      const response = await http.delete(
        `/user/interest/delete?userid=${this.interestInfo.userid}&aptCode=${this.interestInfo.aptCode}`
      );
      if (response.data == 1) {
        await this.callInterestList();
      } else {
        alert("관심하우스 삭제 실패");
      }
    },

    moveToApt(y, x) {
      const position = new kakao.maps.LatLng(y, x);
      this.map.setLevel(1);
      this.map.panTo(position);
    },
    UpdateCost(e) {
      this.costMinValue = e.minValue;
      this.costMaxValue = e.maxValue;
    },

    UpdateArea(e) {
      this.areaMinValue = e.minValue;
      this.areaMaxValue = e.maxValue;
    },

    clickOptions(e) {
      if (e.currentTarget.id == "filters") {
        this.showInterest = false;
      } else {
        this.showFilter = false;
      }
    },

    clickSetting() {
      if (this.saveSetting) {
        this.saveSetting = false;
      } else {
        this.saveSetting = true;
      }
      this.showFilter = false;
    },
  },
  filters: {
    price(val) {
      let value1 = Math.floor(val / 10000);
      let value2 = val % 10000;
      if (value2 != 0) {
        return value1 + "억 " + value2 + "만원";
      } else if ((value2 == 0) & (value1 != 0)) {
        return value1 + "억";
      } else {
        return "0원";
      }
    },
  },
};
</script>

<style>
.map {
  width: 100%;
  min-height: 100vh;
}
.custom-overlay-box {
  position: relative;
  width: 128px;
  height: 128px;
  background-image: url("@/assets/overlay.png");
  padding: 15px 10px;
}
.house-title {
  margin-top: 5px;
  margin-left: 5px;
}
.deal-amount {
  font-size: 18px;
  margin-left: 5px;
}
.area {
  font-size: 8px;
  margin-left: 5px;
}
.recent-date {
  font-size: 8px;
}

#interest-list-group {
  padding: 0px;
}

.overlay {
  cursor: pointer;
}

.list-group-item {
  border: 0px;
}
.multi-range-slider * {
  box-sizing: border-box;
  padding: 0px;
  margin: 0px;
}
.multi-range-slider {
  display: flex;
  position: relative;
  border: none;
  border-radius: 10px;
  padding: 20px 10px;
  box-shadow: none;
  flex-direction: column;
  -webkit-touch-callout: none; /* iOS Safari */
  -webkit-user-select: none; /* Safari */
  -khtml-user-select: none; /* Konqueror HTML */
  -moz-user-select: none; /* Old versions of Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none; /* Non-prefixed version, currently supported by Chrome, Edge,*/
}
.multi-range-slider .bar {
  display: flex;
}
.multi-range-slider .bar-left {
  width: 25%;
  background-color: #c4c4c4;
  border-radius: 10px 0px 0px 10px;
  box-shadow: none;
  padding: 4px 0px;
}
.multi-range-slider .bar-right {
  width: 25%;
  background-color: #c4c4c4;
  border-radius: 0px 10px 10px 0px;
  box-shadow: none;
}
/* range inner bar */
.multi-range-slider .bar-inner {
  background-color: #343a40;
  display: flex;
  flex-grow: 1;
  flex-shrink: 1;
  justify-content: space-between;
  position: relative;
  border: solid 1px black;
  justify-content: space-between;
  box-shadow: inset 0px 0px 5px black;
}
.multi-range-slider .bar-inner-left {
  width: 50%;
}
.multi-range-slider .bar-inner-right {
  width: 50%;
}
.multi-range-slider .thumb {
  background-color: red;
  position: relative;
  z-index: 1;
  cursor: pointer;
}
/* 동그라미 버튼*/
.multi-range-slider .thumb::before {
  content: "";
  background-color: white;
  position: absolute;
  width: 20px;
  height: 20px;
  border: 1px solid black;
  border-radius: 50%;
  box-shadow: none;
  z-index: 1;
  margin: -8px;
  cursor: pointer;
}
.multi-range-slider .input-type-range:focus + .thumb::after {
  content: "";
  position: absolute;
  top: -4px;
  left: -4px;
  width: 11px;
  height: 11px;
  z-index: 2;
  border-radius: 50%;
  border: dotted 1px black;
  box-shadow: 0px 0px 5px white, inset 0px 0px 10px black;
}
.multi-range-slider .caption {
  position: absolute;
  bottom: 35px;
  width: 2px;
  height: 0px;
  top: -20px;
  left: 1px;
  display: flex;
  align-items: center;
  overflow: visible;
  display: none;
  color: rgba(0, 0, 0, 0);
}
.multi-range-slider .thumb .caption * {
  position: absolute;
  min-width: 30px;
  height: 30px;
  font-size: 75%;
  text-align: center;
  line-height: 30px;
  background-color: rgba(86, 79, 79, 0);
  border-radius: 15px;
  color: black;
  box-shadow: none;
  padding: 0px;
  white-space: nowrap;
  color: rgba(0, 0, 0, 0);
}
.multi-range-slider .thumb:active .caption {
  display: flex;
}
.multi-range-slider .input-type-range:focus + .thumb .caption {
  display: flex;
}
.multi-range-slider .input-type-range {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  opacity: 0;
  pointer-events: none;
}

.multi-range-slider .ruler {
  margin: 10px 0px -5px 0px;
  display: flex;
  /* display: none; */
  overflow: hidden;
}
.multi-range-slider .ruler .ruler-rule {
  border-left: solid 1px;
  border-bottom: solid 1px;
  display: flex;
  flex-grow: 1;
  flex-shrink: 1;
  padding: 5px 0px;
}
.multi-range-slider .ruler .ruler-rule:last-child {
  border-right: solid 1px;
}

.multi-range-slider .sub-ruler {
  margin: -2px 0px -5px 0px;
  display: flex; /*
display: none; */
}
.multi-range-slider .sub-ruler .ruler-sub-rule {
  border-left: solid 1px;
  border-bottom: solid 1px;
  display: flex;
  flex-grow: 1;
  flex-shrink: 1;
  padding: 3px 0px;
}
.multi-range-slider .sub-ruler .ruler-sub-rule:last-child {
  border-right: solid 1px;
}

.multi-range-slider .labels {
  display: flex;
  justify-content: space-between;
  padding: 0px;
  margin-top: 10px;
  margin-bottom: -20px;
  /* display: none; */
}
.multi-range-slider .label {
  font-size: 80%;
  display: flex;
  width: 1px;
  justify-content: center;
}
.multi-range-slider .label:first-child {
  justify-content: start;
}
.multi-range-slider .label:last-child {
  justify-content: end;
}
.multi-range-slider.zero-ranage-margin .thumb-left {
  right: 12px;
}
.multi-range-slider.zero-ranage-margin .thumb-right {
  left: 8px;
}

.btn {
  color: #fff;
  background-color: #fff;
  cursor: default;
}
.btn:active,
.btn:focus {
  border: none;
  box-shadow: none;
}

.btn:active,
.btn:focus {
  border: none !important;
  box-shadow: none !important;
}
</style>
