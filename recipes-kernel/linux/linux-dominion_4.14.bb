require linux.inc

DEPENDS += "openssl-native"

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(rogue|dominion-old|dominion|beast|macbook|soekris-net6501|arietta-g25|macbook|minnow|minnowboard|fri2|beaglebone|apu2c4|revo|dlink-dns320)"

FILESPATH =. "${FILE_DIRNAME}/linux-dominion-4.14:${FILE_DIRNAME}/linux-dominion-4.14/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "4.14.7"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=https;branch=linux-4.14.y"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=https;branch=master"
SRCREV_pn-${PN} = "3afae8437c3cbc22966762e80e81818f5a90eb06"


SRC_URI += " \
             file://0001-wireless-populate-db.txt.patch \
             file://0001-bonding-sane-default-value-for-MAX_BONDS.patch \
             file://0002-hwmon-k10temp-Move-chip-specific-code-into-probe-fun.patch \
             file://0003-hwmon-k10temp-Add-support-for-family-17h.patch \
             file://0004-hwmon-k10temp-Add-support-for-temperature-offsets.patch \
             file://0005-Fix-vdso-link-error.patch \
             file://bd00b873274664f077142c5826ac945f2c63b532.patch \
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
            "

KERNEL_CONFIG_FRAGMENTS_append = " \
                                  ${WORKDIR}/bbr.fragment \
                                  ${WORKDIR}/iosched.fragment \
                                  ${WORKDIR}/block.fragment \
                                  ${WORKDIR}/btrfs.fragment \
                                  ${WORKDIR}/debug.fragment \
                                  ${WORKDIR}/cifs.fragment \
                                  ${WORKDIR}/systemd.fragment \
                                 "

KERNEL_CONFIG_FRAGMENTS_append_x86 = " \
                                  ${WORKDIR}/intel.fragment \
                                 "

KERNEL_CONFIG_FRAGMENTS_append_x86_64 = " \
                                  ${WORKDIR}/intel.fragment \
                                 "

KERNEL_CONFIG_FRAGMENTS_append_dominion = " \
                                  ${WORKDIR}/mlx.fragment \
                                 "

KERNEL_CONFIG_FRAGMENTS_append_beast = " \
				  ${WORKDIR}/mlx.fragment \
                                  ${WORKDIR}/nfs.fragment \
                                 "

KERNEL_CONFIG_FRAGMENTS_append_rogue = " \
                                  ${WORKDIR}/mlx.fragment \
                                 "

do_configure_prepend() {
	if [ -e ${WORKDIR}/am335x-pm-firmware.elf ] ; then
		cp ${WORKDIR}/am* ${S}/firmware/
	fi
}
