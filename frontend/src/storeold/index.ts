import Vue from 'vue'
import Vuex from 'vuex'

import SimpleStoreModule from './SimpleCounterStore'
import PubsStoreModule from './PubsStore'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    simpleCounter: SimpleStoreModule,
    pubs: PubsStoreModule
  }
})
