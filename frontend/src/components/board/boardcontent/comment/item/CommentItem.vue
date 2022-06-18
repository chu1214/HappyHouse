<template>
  <b-tr style="border-bottom: 1px solid gray">
    <div>
      <b-row class="mt-3">
        <b-col align="left" class="ml-3">
          {{ userid }}&nbsp; | &nbsp; {{ regtime | dateFormat }}
        </b-col>
        <b-col
          align="right"
          class="mr-3"
          v-if="this.userid == this.userInfo.userid"
        >
          <b-button variant="complete" size="sm" @click="modifyCommentForm"
            ><b-icon icon="pencil-square" scale="1"></b-icon
          ></b-button>
          <b-button variant="complete" size="sm" @click="deleteComment"
            ><b-icon icon="trash" scale="1"></b-icon
          ></b-button>
        </b-col>
      </b-row>
      <hr />
    </div>
    <div align="left" class="p-3 mb-3" v-if="isNormalMode">
      {{ content }}
    </div>
    <div v-else>
      <b-form-textarea
        rows="8"
        type="text"
        align="left"
        class="p-3"
        v-model="comment.content"
      />
      <div align="right" class="mt-1 mb-1 mr-3">
        <b-button variant="complete" size="sm" @click="modifyComment"
          ><b-icon icon="check" variant="dark" scale="2"></b-icon
        ></b-button>
        <b-button variant="complete" size="sm" @click="cancelModifyComment"
          ><b-icon icon="x" variant="dark" scale="2"></b-icon
        ></b-button>
      </div>
    </div>
  </b-tr>
</template>

<script>
import moment from "moment";
import { mapState } from "vuex";
import { http } from "@/js/http.js";

const memberStore = "memberStore";

export default {
  name: "BoardListItem",
  data() {
    return {
      isNormalMode: true,
      comment: {},
    };
  },
  props: {
    articleno: Number,
    commentno: Number,
    userid: String,
    regtime: String,
    content: String,
  },

  created() {
    this.comment.articleno = this.articleno;
    this.comment.commentno = this.commentno;
    this.comment.userid = this.userid;
    this.comment.content = this.content;
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },

  methods: {
    modifyCommentForm() {
      this.isNormalMode = !this.isNormalMode;
    },

    async modifyComment() {
      try {
        const response = await http.put(
          `/comment/${this.commentno}`,
          this.comment
        );
        console.log(response.data);
        if (response.data == 1) {
          alert("댓글이 수정되었습니다.");
          this.$router.go();
        } else {
          alert("댓글 수정 실패!");
        }
      } catch (error) {
        alert("댓글 수정 실패!");
      }
    },

    cancelModifyComment() {
      this.isNormalMode = !this.isNormalMode;
    },

    async deleteComment() {
      try {
        const response = await http.delete(`/comment/${this.commentno}`);
        console.log(response.data);
        if (response.data == 1) {
          alert("댓글이 삭제되었습니다.");
          this.$router.go();
        } else {
          alert("댓글 삭제 실패!");
        }
      } catch (error) {
        alert("댓글 삭제 실패!");
      }
    },
  },
};
</script>

<style scoped>
a {
  font-weight: 500;
  text-decoration: none;
  color: black;
}
</style>
