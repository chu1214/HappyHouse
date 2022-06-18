<template>
  <div class="mt-3">
    <b-card
      header-bg-variant="dark"
      header-tag="header"
      style="border-radius: 20px"
    >
      <template #header>
        <h3 class="mt-2" style="color: white" align="center">최근 실거래가</h3>
      </template>
      <b-card-body
        ><h2 align="center">
          {{ currentHouse.dealAmount | price }}
        </h2></b-card-body
      >
    </b-card>
    <h2 class="mt-4">
      <b-icon icon="info-circle-fill"></b-icon>
      <span class="ml-3">거래정보</span>
    </h2>
    <b-table-simple
      hover
      class="mt-3"
      style="border-top: 1px solid gray; border-collapse: collapse"
    >
      <b-thead style="display: none">
        <b-th>분류</b-th>
        <b-th>값</b-th>
      </b-thead>
      <b-tbody style="font-size: 20px">
        <b-tr>
          <b-td>주소</b-td>
          <b-td>{{ roadAddress }}</b-td>
        </b-tr>
        <b-tr>
          <b-td>거래일자</b-td>
          <b-td>{{ currentHouse.recentDate | date }}</b-td>
        </b-tr>
        <b-tr>
          <b-td>층</b-td>
          <b-td>{{ currentHouse.floor }}</b-td>
        </b-tr>
        <b-tr>
          <b-td>면적</b-td>
          <b-td>{{ currentHouse.area }}m<sup>2</sup></b-td>
        </b-tr>
        <b-tr>
          <b-td>건축년도</b-td>
          <b-td>{{ currentHouse.buildYear }}년</b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
  </div>
</template>

<script>
export default {
  name: "BasicInfo",

  props: {
    currentHouse: Object,
    roadAddress: String,
  },

  data() {
    return {};
  },

  mounted() {},

  methods: {},

  filters: {
    price(val) {
      let value1 = Math.floor(String(val).replace(/[^0-9]/g, "") / 10000);
      let value2 = String(val).replace(/[^0-9]/g, "") % 10000;
      if (value2 != 0) {
        return value1 + "억 " + value2 + "만원";
      } else {
        return value1 + "억 ";
      }
    },
    date(val) {
      var value = val + "";
      return (
        value.substr(0, 4) + "." + value.substr(4, 2) + "." + value.substr(6, 2)
      );
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
</style>
