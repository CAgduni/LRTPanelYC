# Provides the i.MX common settings

require conf/machine/include/tune-cortexa9.inc

#IMAGE_CLASSES = "image_types_uboot"
IMAGE_CLASSES = "image_types_cannon"


# Ship kernel modules
MACHINE_EXTRA_RRECOMMENDS = "kernel-modules"

# Tunes for hard/soft float-point selection. Note that we allow building for
# thumb support giving distros the chance to enable thumb by setting
# ARM_INSTRUCTION_SET = "thumb"
#
# handled by software
# DEFAULTTUNE_mx6 ?= "cortexa9t-neon"
# handled by hardware
DEFAULTTUNE_mx6 ?= "cortexa9thf-neon"
DEFAULTTUNE_mx6ul ?= "cortexa7thf-neon"
DEFAULTTUNE_mx7 ?= "cortexa7thf-neon"
DEFAULTTUNE_vf ?= "cortexa5thf-neon"


MACHINEOVERRIDES_EXTENDER_mx6q  = "imxipu:imxvpu:imxgpu2d:imxgpu3d"
MACHINEOVERRIDES_EXTENDER_mx6dl = "imxpxp:imxipu:imxvpu:imxgpu2d:imxgpu3d:imxepdc"

# Sub-architecture support
MACHINE_SOCARCH_SUFFIX ?= ""
MACHINE_SOCARCH_SUFFIX_mx6q = "-mx6qdl"
MACHINE_SOCARCH_SUFFIX_mx6dl = "-mx6qdl"

MACHINE_ARCH_FILTER = "virtual/kernel"

# FIXME: Needs addition of firmware-imx of official BSPs
#MACHINE_FIRMWARE_append_mx27 = " firmware-imx-vpu-imx27"
#MACHINE_FIRMWARE_append_mx25 = " firmware-imx-sdma-imx25"

MACHINE_EXTRA_RRECOMMENDS += "${MACHINE_FIRMWARE}"


# Extra udev rules
MACHINE_EXTRA_RRECOMMENDS += "udev-rules-imx"


# Handle default kernel
IMX_DEFAULT_KERNEL = "linux-4.14.39"
IMX_DEFAULT_KERNEL_mx6 = "linux-4.14.39"


PREFERRED_PROVIDER_virtual/kernel ??= "${IMX_DEFAULT_KERNEL}"

IMAGE_FSTYPES ?= "sdcard"

#6GB
IMAGE_ROOTFS_SIZE ?= "6291456"


SERIAL_CONSOLE = "115200 ttymxc0"

KERNEL_IMAGETYPE = "zImage"

MACHINE_FEATURES = "usbgadget usbhost vfat"

# Add the ability to specify _imx machines
MACHINEOVERRIDES =. "imx:"
