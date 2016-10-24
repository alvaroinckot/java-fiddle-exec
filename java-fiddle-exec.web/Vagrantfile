# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  config.vm.box = "ubuntu/trusty64"

  config.vm.network "forwarded_port", guest: 4568, host: 8083

  config.vm.network "private_network", ip: "192.168.33.11"

  config.vm.provider "virtualbox" do |vb|
    vb.memory = "1024"
    vb.cpus = 1
  end

  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update
    sudo apt-get install -q -y linux-image-generic-lts-trusty
    sudo apt-get install -q -y python-pip
    sudo pip install --upgrade pip
    sudo pip install --upgrade virtualenv
    # install docker
    sudo curl -sSL https://get.docker.com/ | sh
    sudo usermod -aG docker vagrant
    sudo systemctl enable docker
    # install docker-compose
    sudo pip install -U docker-compose==1.8.0
    # setup default folder on ssh
    sudo echo "cd /vagrant" >> /home/vagrant/.bashrc
  SHELL
  
end
