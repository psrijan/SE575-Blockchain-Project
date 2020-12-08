<template>
  <div>
    <img alt="Vue logo" src="../assets/logo.png" width="300">
    <b-container>
      <web-services-demo></web-services-demo>
    </b-container>
  </div>
</template>


<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import ServicesParent from '@/components/BlockChainMain.vue'

@Component({
  components: {"web-services-demo": ServicesParent}
})
export default class WebServicesView extends Vue {

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
