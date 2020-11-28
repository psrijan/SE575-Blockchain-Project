<template>
  <div>
      <b-container>
          <b-card>
              <b-card-text>
                  <h2>About text goes here</h2>
              </b-card-text>
          </b-card>
      </b-container>
  </div>
</template>
  

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class AboutHeader extends Vue {

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>