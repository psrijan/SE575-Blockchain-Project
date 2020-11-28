<template>
  <div class="about">
    <h1>Demo Using Comonents - Personal Page</h1>

    <about-header></about-header>
    <about-background></about-background>
    <about-teaching></about-teaching>
    <about-publications></about-publications>
    <about-photos></about-photos>

  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

import AboutHeader from '@/components/About/AboutHeader.vue'
import AboutBackground from '@/components/About/AboutBackground.vue'
import AboutPublications from '@/components/About/AboutPublications.vue'
import AboutTeaching from '@/components/About/AboutTeaching.vue'
import AboutPhotos from '@/components/About/AboutPhotos.vue'

@Component({ 
  components: { 
    "about-header": AboutHeader,
    "about-background": AboutBackground,
    "about-publications": AboutPublications,
    "about-teaching": AboutTeaching,
    "about-photos": AboutPhotos
  } 
})
export default class About extends Vue {

}
</script>
