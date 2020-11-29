<template>
  <div>
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
                  ></b-form-input>
                </b-input-group>
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
                <!--
                  <b-button variant="primary" class="ml-2" @click="getAll()"
                    >Get All</b-button
                  >
                -->
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
        <pub-child
          :card-data="block"
        ></pub-child>
      </div>
    </div>
  </div>
</template>


<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Pub from "./CardElement.vue";
import { BlockElement, BlockCreate } from "./BlockChainTypes";

@Component({ components: { "pub-child": Pub } })
export default class ServiceParent extends Vue {
  private blockList: BlockElement[] = [];
  private defaultServerAddress = "http://localhost:3000";
  private showErrorBanner = false;
  private errorMessage = "";
  private showOkBanner = false;
  private okMessage = "";
  private newBlockData = "";
  private difficulty = "000";
  private attempts = 10000;


  createModal() {
    this.newBlockData = "";
    this.$bvModal.show('create-modal')
  }
  createBlock() {
    console.log("Create Block");
    this.showErrorBanner = false;
    this.showOkBanner =  false;

    /* Hide modal and capture data as post payload */
    this.$bvModal.hide('create-modal');
    const payload: BlockCreate = {data: this.newBlockData};
    console.log(payload);
    const endpoint = this.defaultServerAddress + "/blockchain";

    /* Make post request to the server */
    this.$http.post(endpoint, payload).then((response) => {
      const result = response.data;
      // this.blockList = result;
      this.okMessage = response.statusText;
      this.showOkBanner = true;
      console.log(result);
      this.getAll();
    }).catch(error => {
      this.errorMessage = "ERROR: " + error.message;
      this.showErrorBanner = true;
      console.error("There was an error!", error);
    });
  }

  getAll() {
    console.log("getall");
    this.showErrorBanner = false;
    this.showOkBanner =  false;
    this.blockList = [];
    const endpoint = this.defaultServerAddress + "/blockchain";
    this.$http.get<BlockElement[]>(endpoint).then((response) => {
      const result = response.data;
      this.blockList = result;
      this.okMessage = "Fetched All Blocks - Total Received: " + this.blockList.length ;
      this.showOkBanner = true;
      console.log(result);
    });
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
