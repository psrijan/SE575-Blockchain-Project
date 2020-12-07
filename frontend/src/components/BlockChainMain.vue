<template>
  <div @update_me="update">
    <h1> Blockchain Application </h1>
    <div class="row">
      <b-container>
        <b-card class="col-12" bg-variant="dark" text-variant="white">
          <b-card-text>
            Before you start this demo make sure you have
            <code>json-server</code> installed via <br />
            <code>yarn global add json-server</code> or
            <code>npm install -g json-server</code>
            <br />
            After that run the server from the <code>/server</code> directory
            via
            <br />
            <code>start-test-server.sh</code>
            <br />
            Currently, only <code>Get All</code> will produce a useful result from the <code>blockchain</code> db.
          </b-card-text>
          <b-card-text>
            <b-row class="my-1">
              <b-col sm="2" class="offset-sm-2">
                <label for="server-address">Server Address:</label>
              </b-col>
              <b-col sm="6">
                <b-form-input
                  id="server-address"
                  v-model="defaultServerAddress"
                ></b-form-input>
              </b-col>
            </b-row>
            <b-row class="my-1">
              <b-col sm="3" class="offset-sm-2">
                <b-input-group prepend="Difficulty:">
                  <b-form-input
                    id="difficulty-id"
                    type="text"
                    v-model="difficulty"
                  ></b-form-input> </b-input-group>
              </b-col>
              <b-col sm="3">
                <b-input-group prepend="Attempts:">
                  <b-form-input
                    id="attempts-id"
                    type="number"
                    v-model="attempts"
                  ></b-form-input>
                </b-input-group>
              </b-col>
              <b-col sm="3">
                  <b-button variant="success" v-b-modal.create-modal @click="createModal()">Create a new Block</b-button>
                  <b-modal id="create-modal" title="Create a new Block">
                    <div class="d-block text-center">
                      <p>
                        Please enter the new block data:
                      </p>
                      <b-form-textarea
                        id="block-data"
                        v-model="newBlockData"
                        rows="3"
                      ></b-form-textarea>
                    </div>
                    <template #modal-footer>
                      <div class="w-100">
                        <b-button
                          variant="primary"
                          size="sm"
                          class="float-right"
                          @click="createBlock()"
                        >
                          Create
                        </b-button>
                      </div>
                    </template>
                  </b-modal>
              </b-col>
            </b-row>
            <b-row>
              <b-col sm="8" class="offset-sm-2">
                <b-alert fade :show="showErrorBanner" dismissible variant="danger" @dismissed="showErrorBanner=false">
                  {{ errorMessage }}
                </b-alert>
              </b-col>
            </b-row>
            <b-row>
              <b-col sm="8" class="offset-sm-2">
                <b-alert fade :show="showOkBanner" dismissible variant="success" @dismissed="showOKBanner=false">
                  {{ okMessage }}
                </b-alert>
              </b-col>
            </b-row>
          </b-card-text>
        </b-card>
      </b-container>
    </div>
    <hr />
    <div class="row">
      <div
        class="col-sm-6 mb-2"
        v-for="(block, index) in blockList"
        :key="index"
      >
        <block-card
          :card-data="block" :base-url="defaultServerAddress" 
        ></block-card>
      </div>
    </div>
  </div>
</template>


<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { BlockElement, BlockCreate } from "@/models/BlockChainTypes";
import BlockCard from "./BlockCard.vue";
import  { HttpService } from "@/services/HttpService"
import {BaseMessage} from "@/models/intermediatedtos"

@Component({ components: { "block-card": BlockCard} })
export default class ServiceParent extends Vue {
  private blockList: BlockElement[] = [];
  private defaultServerAddress = "http://localhost:8080";
  private showErrorBanner = false;
  private errorMessage = "";
  private showOkBanner = false;
  private okMessage = "";
  private newBlockData = "";
  private difficulty = "000";
  private attempts = 10000;
  private httpService = new HttpService(this.defaultServerAddress);

  createModal() {
    this.newBlockData = "";
    this.$bvModal.show('create-modal')
  }

  mounted() {
    const messagePromise: Promise<BaseMessage> = this.httpService.getAll();
    messagePromise.then(msg => {
        if (msg.success) {
            this.okMessage = msg.message;
            this.showOkBanner = true;
            this.blockList = msg.dto;
            console.log(this.blockList)
        } else {
            this.errorMessage = msg.message;
            this.showErrorBanner = true;
        }
    });
  }

  createBlock() {
    console.log("Create Block");
    this.showErrorBanner = false;
    this.showOkBanner =  false;
    /* Hide modal and capture data as post payload */
    this.$bvModal.hide('create-modal');
    const payload: BlockCreate = {data: this.newBlockData, difficulty: this.difficulty, attempts: this.attempts};
    console.log(payload);
    const messagePromise: Promise<BaseMessage> = this.httpService.addBlock(payload);
    messagePromise.then(msg => {
        if (msg.success) {
            this.okMessage = msg.message;
            this.showOkBanner = true;
            const getAllPromise: Promise<BaseMessage> = this.httpService.getAll();
             getAllPromise.then(msg => {
                if (msg.success) {
                    this.blockList = msg.dto;
                    this.okMessage  = msg.message;
                    this.showOkBanner = true;
                }
            }).catch(error => {
                this.errorMessage = msg.message;
                this.showErrorBanner = true;
            });
            console.log(this.blockList)
        } else {
            this.errorMessage = msg.message;
            this.showErrorBanner = true;
        }
    });
    }
    
    update() {
        console.log("Get All For Update...");
        const messagePromise: Promise<BaseMessage> = this.httpService.getAll();
        messagePromise.then(msg => {
        if (msg.success) {
            this.okMessage = msg.message;
            this.showOkBanner = true;
            this.blockList = msg.dto;
        } else {
            this.errorMessage = msg.message;
            this.showErrorBanner = true;
        }
        });
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
