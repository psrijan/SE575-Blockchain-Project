<template>
  <div>
      <b-container>
          <b-card class="text-white bg-danger">
              <b-card-text>
                  <h2>Background Text Goes Here</h2>
              </b-card-text>
          </b-card>
      </b-container>
  </div>
</template>
  

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class AboutBackground extends Vue {

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>