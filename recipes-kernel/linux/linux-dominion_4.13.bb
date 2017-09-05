require linux.inc

DEPENDS += "openssl-native"

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(rogue|dominion-old|dominion|beast|macbook|soekris-net6501|arietta-g25|macbook|minnow|minnowboard|fri2|beaglebone|apu2c4|revo|dlink-dns320)"

FILESPATH =. "${FILE_DIRNAME}/linux-dominion-4.13:${FILE_DIRNAME}/linux-dominion-4.13/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "4.13.0"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=https;branch=linux-4.13.y"
SRCREV_pn-${PN} = "569dbb88e80deb68974ef6fdd6a13edb9d686261"


SRC_URI += " \
             file://0001-wireless-populate-db.txt.patch \
             file://0001-bonding-sane-default-value-for-MAX_BONDS.patch \
             file://am335x-pm-firmware.elf \
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
                                 "

KERNEL_CONFIG_FRAGMENTS_append_rogue = " \
                                  ${WORKDIR}/mlx.fragment \
                                 "

do_configure_prepend() {
	if [ -e ${WORKDIR}/am335x-pm-firmware.elf ] ; then
		cp ${WORKDIR}/am335x-pm-firmware.elf ${S}/firmware/
	fi
}
