<template>
  <div class="mt-3">
    <b-card
      header-bg-variant="dark"
      header-tag="header"
      style="border-radius: 20px"
    >
      <template #header>
        <h3 class="mt-2" style="color: white" align="center">평균 실거래가</h3>
      </template>
      <b-card-body
        ><h2 align="center">
          {{ avgDeals | price }}
        </h2>
        <div style="font-size: 15px; color: gray" align="center">
          {{ currentHouse.dong }} 평균 거래가 {{ avgDongDeals | price
          }}{{ comment }}
        </div>
      </b-card-body>
    </b-card>
    <div class="mt-4">
      <h2>
        <b-icon icon="building"></b-icon> <span class="ml-2">세부내역</span>
      </h2>
    </div>
    <b-list-group
      id="dealHistory"
      style="
        width: 520px;
        text-align: left;
        max-height: 300px;
        overflow-y: auto;
      "
      class="mt-3"
    >
      <b-list-group-item
        v-for="(item, index) in deals"
        :key="index"
        button
        style="cursor: default"
      >
        <b-row class="pl-4" style="font-size: 20px"
          >{{ item.dealYear }}.{{ item.dealMonth }}.{{ item.dealDay }}</b-row
        >
        <b-row class="pl-2">
          <b-col style="font-size: 30px">{{ item.dealAmount | price }}</b-col>
          <b-col align="">
            <div class="mt-3"></div>
            <div style="font-size: 18px">
              {{ item.area }}m<sup>2</sup> / {{ item.floor }}층
            </div>
          </b-col>
        </b-row>
      </b-list-group-item>
    </b-list-group>
  </div>
</template>

<script>
export default {
  name: "DealInfo",

  props: {
    currentHouse: Object,
    deals: Array,
    dongDeals: Array,
  },

  watch: {
    deals: function () {
      let sum = 0;
      for (let deal of this.deals) {
        sum += Number(deal.dealAmount.replace(/[^0-9]/g, ""));
      }
      sum /= this.deals.length;
      this.avgDeals = sum.toFixed(0);
    },

    dongDeals: function () {
      let sum = 0;
      for (var deal of this.dongDeals) {
        sum += Number(deal.dealAmount.replace(/[^0-9]/g, ""));
      }
      sum /= this.dongDeals.length;
      this.avgDongDeals = sum.toFixed(0);
      this.compareWithDong();
    },
  },

  data() {
    return {
      avgDeals: "",
      avgDongDeals: "",
      comment: "",
    };
  },

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

  mounted() {},

  methods: {
    compareWithDong() {
      let percentage = ((this.avgDeals / this.avgDongDeals - 1) * 100).toFixed(
        1
      );
      if (percentage < 0) {
        this.comment = `보다 ${-percentage}% 낮아요.`;
      } else if (percentage > 0) {
        this.comment = `보다 ${percentage}% 높아요.`;
      } else {
        this.comment = `과 같아요.`;
      }
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

::-webkit-scrollbar-track {
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  background-color: #f5f5f5;
}

::-webkit-scrollbar {
  width: 12px;
  background-color: #f5f5f5;
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #555;
}

.card-body {
  flex: 1 1 auto;
  min-height: 1px;
  padding: 1rem;
}
</style>
