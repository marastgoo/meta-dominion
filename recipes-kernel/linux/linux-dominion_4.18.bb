require linux.inc

DEPENDS += "openssl-native"

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(rogue|dominion-old|dominion|beast|macbook|soekris-net6501|arietta-g25|macbook|minnow|minnowboard|fri2|beaglebone|apu2c4|revo|dlink-dns320)"

FILESPATH =. "${FILE_DIRNAME}/linux-dominion-4.18:${FILE_DIRNAME}/linux-dominion-4.18/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "4.18.14"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=https;branch=linux-4.18.y"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=https;branch=master"
SRCREV_pn-${PN} = "e7d199e92956587695510d147c8de795f944cec9"

SRC_URI += " \
             file://0001-bonding-sane-default-value-for-MAX_BONDS.patch \
             file://0005-Fix-vdso-link-error.patch \
             file://0001-ARM-DTS-add-am335x-sancloud-bbe-device-tree.patch \
             file://0001-block-BFQ-default-for-single-queue-devices.patch \
             file://am335x-bone-scale-data.bin \
             file://am335x-evm-scale-data.bin \
             file://am335x-pm-firmware.bin \
             file://am335x-pm-firmware.elf \
             file://am43x-evm-scale-data.bin \
             file://defconfig \
             file://bbr.fragment \
             file://iosched.fragment \
             file://intel.fragment \
             file://block.fragment \
             file://btrfs.fragment \
             file://debug.fragment \
             file://systemd.fragment \
             file://cifs.fragment \
             file://nfs.fragment \
             file://mlx.fragment \
             file://tweaks.fragment \
             file://unwinder.fragment \
            "

KERNEL_CONFIG_FRAGMENTS_append = " \
                                  ${WORKDIR}/bbr.fragment \
                                  ${WORKDIR}/iosched.fragment \
                                  ${WORKDIR}/block.fragment \
                                  ${WORKDIR}/btrfs.fragment \
                                  ${WORKDIR}/debug.fragment \
                                  ${WORKDIR}/cifs.fragment \
                                  ${WORKDIR}/systemd.fragment \
                                  ${WORKDIR}/tweaks.fragment \
                                  ${WORKDIR}/unwinder.fragment \
                                 "

KERNEL_CONFIG_FRAGMENTS_append_x86 = " \
                                  ${WORKDIR}/intel.fragment \
                                  ${WORKDIR}/nfs.fragment \
                                 "

KERNEL_CONFIG_FRAGMENTS_append_x86-64 = " \
                                  ${WORKDIR}/intel.fragment \
                                  ${WORKDIR}/nfs.fragment \
                                  ${WORKDIR}/mlx.fragment \
                                 "

do_configure_prepend() {
	if [ -e ${WORKDIR}/am335x-pm-firmware.elf ] ; then
		cp ${WORKDIR}/am* ${S}/firmware/
	fi
}
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
