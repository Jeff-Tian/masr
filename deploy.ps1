function askForTarget(){
    Param ([string]$selection=(Read-Host  "(1) UAT - 10.20.32.61           (2) LIVE - 114.55.233.38"))

    Write-Host "you entered $selection"

    Write-Host ($selection -eq "1")

    $res=@{ssh_host=""; working_dir=""; scp_target=""}

    if ($selection -eq "1") {
        $res.ssh_host = "vertica@10.20.32.61"
        $res.working_dir = "/home/vertica/masr/"
        $res.scp_target = "vertica@10.20.32.61:/home/vertica/masr/"
        return $res
    }

    if ($selection -eq "2") {
        Write-Host "Deploying to live server!"
        $res.ssh_host = "root@114.55.233.38"
        $res.working_dir = "/root/masr/"
        $res.scp_target = "root@114.55.233.38:/root/masr/"

        return $res
    }

}

Write-Host "Deploying..."
Add-Type -AssemblyName System.speech
$speak = New-Object System.Speech.Synthesis.SpeechSynthesizer
$speak.Speak('Deploying...')

Write-Host "Select your target:"
$target=askForTarget

Write-Host "deploy to $target.working_dir"
$target
$cmd = "mkdir -p " + $target.working_dir + "  && ls"
$cmd
ssh $target.ssh_host $cmd

scp ./build/libs/masr-0.1.0.jar ./deploy/masr-service.sh $target.scp_target

ssh $target.ssh_host "pwd && ls " $target.working_dir  " && cd "  $target.working_dir  "&& chmod +x masr-service.sh && ./masr-service.sh stop && ./masr-service.sh start masr-0.1.0.jar && sleep 2 && curl localhost:12444/healthcheck"
