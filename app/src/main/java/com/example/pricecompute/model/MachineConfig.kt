package com.example.pricecompute.model

data class MachineConfig(
    val machineName:String,
    val desc:String,
    val price:Double,
    val plan: Plan,
    var duration:Long = 30
){
    companion object{
        val machineList:List<MachineConfig> = listOf(
            MachineConfig("EC2","Virtual Servers in the Cloud",0.023,Plan(12,null,128,256)),
            MachineConfig("S3","Scalable Storage in the Cloud",0.023,Plan(16,null,256,256)),
            MachineConfig("RDS","Managed Relational Database Service",0.017,Plan(8,null,128,256)),
            MachineConfig("Lambda","Run Code without Thinking about Servers",0.0028,Plan(12,null,128,256)),
            MachineConfig("CloudFront","Content Delivery Network",0.085,Plan(24,null,512,512)),
            MachineConfig("DynamoDB","NoSQL Database Service",0.011,Plan(6,null,128,256)),
        )

        var currentMachine = machineList[0]
    }
}
