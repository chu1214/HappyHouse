<template>
  <b-container class="position-absolute" style="z-index: 1000">
    <b-row>
      <!--  -->
      <b-col>
        <b-container>
          <b-form>
            <b-input
              style="width: 290px"
              @keyup="searchPlaces"
              type="text"
              v-model="keyword"
              placeholder="검색어를 입력하세요."
              class="mt-2"
            />
          </b-form>
        </b-container>
        <b-container v-show="showResult">
          <b-list-group
            id="searchResult"
            style="width: 290px; text-align: left"
            class
          >
            <b-list-group-item
              v-for="(item, index) in searchResult"
              :key="index"
              button
              @click="moveToClicked(item)"
            >
              <b-row class="resultMainItem pl-2">{{ item.place_name }}</b-row>
              <b-row class="resultSubItem pl-2">{{ item.category_name }}</b-row>
              <b-row class="resultSubItem pl-2">{{
                item.road_address_name
              }}</b-row>
            </b-list-group-item>
          </b-list-group>
        </b-container>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "SearchBar",
  props: {
    searchResult: Array,
    showResult: Boolean,
    userInterest: Array,
  },

  data() {
    return {
      keyword: "",
    };
  },

  methods: {
    searchPlaces() {
      if (this.keyword == "") {
        this.$emit("makeShowFalse");
      }
      this.$emit("searchPlaces", this.keyword);
    },
    moveToClicked(item) {
      this.$emit("resultClick", {
        y: item.y,
        x: item.x,
      });
    },
  },
};
</script>

<style scoped>
#searchResult {
  max-height: 500px;
  overflow-y: auto;
}

.resultMainItem {
  color: black;
  font-size: 15px;
}
.resultSubItem {
  color: gray;
  font-size: 12px;
}

body {
  -ms-overflow-style: none;
}
::-webkit-scrollbar {
  display: none;
}

input:focus {
  outline: 1px solid rgba(0, 0, 0, 0.685);
  border-color: rgba(0, 0, 0, 0.685);
  box-shadow: 0px 0px 5px 3px rgba(0, 0, 0, 0.685);
}
</style>
