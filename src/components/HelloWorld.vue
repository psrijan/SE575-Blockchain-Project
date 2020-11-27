<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>To build and run this app make sure you have the following installed:</p>
    <ul>
      <li>
        <a href="https://nodejs.org/en/" target="_blank" rel="noopener"
          >NodeJS</a
        >
      </li>
      <li>
        <a href="https://yarnpkg.com/cli/node" target="_blank" rel="noopener"
          >yarn</a
        >
      </li>
      <li>
        <a
          href="https://www.typescriptlang.org/#installation"
          target="_blank"
          rel="noopener"
          >typescript</a
        >
      </li>
    </ul>
    <h3>Setup Steps</h3>
    <b-list-group>
      <b-list-group-item>Pull the repo from github - <code>git clone https://github.com/ArchitectingSoftware/se575-web-design-vue.git</code></b-list-group-item>
      <b-list-group-item>Prep the project dependencies - <code>yarn install</code></b-list-group-item>
      <b-list-group-item>Install JSON Server - <code>yarn global add json-server</code></b-list-group-item>
      <b-list-group-item>Prep IDE, If VS Code - add Vetur Plugin</b-list-group-item>
      <b-list-group-item>For the API demo, start the server - run <code>start-test-server.sh</code> from the <code>/server</code> directory.</b-list-group-item>
    </b-list-group>

    <h3>HAVE FUN</h3>   
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class HelloWorld extends Vue {
  @Prop() private msg!: string;
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
