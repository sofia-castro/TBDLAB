<template>
    <div class="container">
    <h1>Todas las emergencias</h1>
    <ul class="item-list">
      <list-item v-for="(item, index) in items" :key="index" :item="item">
        {{item.titulo}}
      </list-item>
    </ul>
    <div v-if="items.length==0" class="empty-list">
      <em>No se han cargado los datos</em>
    </div>
  </div>
</template>
<script>
export default {
    //Función que contiene los datos del componente
    data(){
        return{
            //Lista de ítems a mostrar
            items:[]
        }
    },
    methods:{
        //Función asíncrona para consultar los datos
        getData: async function(){
            try {
                let response = await this.$axios.get('http://localhost:8080/emergencias/');
                this.items  = response.data;
                console.log(response)
            } catch (error) {
                console.log('error', error);
            }
        }
    },
    //Función que se ejecuta al cargar el componente
    created:function(){
        this.getData();
    }
}
</script>