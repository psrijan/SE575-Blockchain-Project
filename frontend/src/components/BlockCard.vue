<template>
  <div>
    <b-card title="Block Record"  v-bind:class="{ active: cardData.valid, 'alert-warning ': !cardData.valid}">
      <b-card-text> Details </b-card-text> <b-card-text> <b-container>
          <b-row class="my-1">
            <b-col sm="3" class="offset-sm-2 text-left">
              <label>BlockID:</label>
            </b-col>
            <b-col sm="6">
              <b-form-input
                size="sm"
                v-model="cardData.blockName"
                readonly
              ></b-form-input>
            </b-col>
          </b-row>

          <b-row class="my-1">
            <b-col sm="3" class="offset-sm-2 text-left">
              <label>Parent hash:</label>
            </b-col>
            <b-col sm="6">
              <b-form-input size="sm" v-model="cardData.previousHash" readonly></b-form-input>
            </b-col>
          </b-row>

          <b-row class="my-1">
            <b-col sm="3" class="offset-sm-2 text-left">
              <label> Attempts:</label>
            </b-col>
            <b-col sm="6" v-if="cardData.attempts !== cardData.nonce">
              <b-form-input size="sm" v-model="cardData.nonce" readonly
              v-bind:class="{ 'text-danger  ': cardData.attempts === cardData.nonce}"
              ></b-form-input>
            </b-col>
            <b-col sm="6" v-else>
              <b-form-input :state="false" size="sm" readonly class="text-danger" v-model="err"
              ></b-form-input>
            </b-col>
          </b-row>

          <b-row class="my-1">
            <b-col sm="3" class="offset-sm-2 text-left">
              <label>Execution Time:</label>
            </b-col>
            <b-col sm="6">
              <b-form-input size="sm" v-model="cardData.executionTime" readonly></b-form-input>
            </b-col>
          </b-row>

          <b-row class="my-1">
            <b-col sm="3" class="offset-sm-2 text-left">
              <label>Block Hash:</label>
            </b-col>
            <b-col sm="6">
              <b-form-input size="sm" v-model="cardData.hash"></b-form-input>
            </b-col>
          </b-row>

          <b-row class="my-1">
            <b-col sm="3" class="offset-sm-2 text-left">
              <label>Data:</label>
            </b-col>
            <b-col sm="6">
              <b-form-input size="sm" v-model="cardData.data"></b-form-input>
            </b-col>
          </b-row>

            <b-row class="my-1">
            <b-col sm="3" class="offset-sm-2 text-left">
              <label>Attempt Limit:</label>
            </b-col>
            <b-col sm="6">
              <b-form-input
                      size="sm"
                      type="number"
                      v-model="cardData.attempts"
              ></b-form-input>
            </b-col>
          </b-row>

          <b-row class="my-1">
          </b-row>
          <b-row class="my-2">
            <b-col sm="3" class="offset-sm-3">
              <b-button
                variant="primary"
                size="sm"
                @click="updateClass()"
                >Update</b-button
              ></b-col>
            <b-col sm="3" class="offset-sm-3">
              <b-button
                :disabled="cardData.valid"
                variant="primary"
                size="sm"
                @click="updateClass()"
                >Remine</b-button
              >
            </b-col>
          </b-row>
        </b-container>
      </b-card-text>
    </b-card>
  </div>
</template>
  

<script lang="ts">
import { Component, Prop, Watch, Vue } from "vue-property-decorator";
import {BlockElement} from "@/models/BlockChainTypes";
import {BaseMessage} from "@/models/intermediatedtos"
import { HttpService } from "@/services/HttpService"
import {MAXATTEMPTSERROR} from '@/constants/Constants';

@Component
export default class BlockCard extends Vue {
  @Prop() private cardData!: BlockElement;
  @Prop() private baseUrl!: string;
  private cardDataChanged = false;
  httpService = new HttpService(this.baseUrl);
  private err = MAXATTEMPTSERROR;

  updateClass() {
    console.log("update button clicked!!");
    console.log(this.cardData.blockName);
    const updateRespPromise: Promise<BaseMessage> = this.httpService.update(this.cardData.data, this.cardData.blockName, this.cardData.attempts);
    updateRespPromise.then((resp) => {
      console.log(resp);
      if (resp.success) {
        this.$emit('update_me')
      }
      this.cardDataChanged = false;
    })
  }


  @Watch('cardData', {immediate: false, deep: true})
  onCardDataChanged(){
      this.cardDataChanged = true;
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>

