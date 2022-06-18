<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col align="center">
        <h1 class="underline mt-5">글작성</h1>
      </b-col>
    </b-row>
    <board-input-item class="mt-5" type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style scoped>
.underline {
  text-align: center;
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(84, 86, 92, 0.678) 30%
  );
}
</style>
