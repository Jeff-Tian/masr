function askForTarget(){
Param ([string]$selection=(Read-Host  "(1) UAT - 10.20.32.61           (2) LIVE - todo"))
Write-Host "you entered $selection"
return "vertica@10.20.32.61:/home/vertica/masr/"
}

Write-Host "Deploying..."
Add-Type -AssemblyName System.speech
$speak = New-Object System.Speech.Synthesis.SpeechSynthesizer
$speak.Speak('Deploying...')

Write-Host "Select your target:"
$target=askForTarget

ssh vertica@10.20.32.61 "mkdir -p /home/vertica/masr"

scp ./build/libs/masr-0.1.0.jar $target
scp ./deploy/masr-service.sh $target

ssh vertica@10.20.32.61 "pwd && cd /home/vertica/masr && chmod +x masr-service.sh && ./masr-service.sh stop && ./masr-service.sh start"