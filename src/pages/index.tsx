// @ts-ignore
import { Fragment, useState } from 'react'
import { Dialog, Disclosure, Popover, Transition, Tab } from '@headlessui/react'
import {
  ArrowPathIcon,
  Bars3Icon,
  ChartPieIcon,
  CursorArrowRaysIcon,
  FingerPrintIcon,
  SquaresPlusIcon,
  XMarkIcon,
} from '@heroicons/react/24/outline'
import { ChevronDownIcon, PhoneIcon, PlayCircleIcon } from '@heroicons/react/20/solid'
import { CloudArrowUpIcon, LockClosedIcon } from '@heroicons/react/24/outline'
import { CheckIcon } from '@heroicons/react/20/solid'
import { ChevronLeftIcon, ChevronRightIcon } from '@heroicons/react/solid';



const features = [
  {
    name: 'Tramites Online',
    description:
      'Utiliza nuestra plataforma para pedir actas, traducir o empezar el proceso de obtencion de la ciudadania.',
    icon: CloudArrowUpIcon,
  },
  {
    name: 'Seguimiento',
    description:
      'Soporte en Argentina y en Italia, seguimiento personal para agilizar la obtencion de los documentos necesarios.',
    icon: SquaresPlusIcon,
  },
  {
    name: 'Legalizacion y traducciones',
    description:
      'Traducciones en menor tiempo, con posibilidad de legalizacion.',
    icon: FingerPrintIcon,
  },
  {
    name: 'Actas y Partidas',
    description:
      'Partidas de matrimonio, defuncion o nacimiento enviadas tramite correo privado.',
    icon: ArrowPathIcon,
  },
]
const products = [
  { name: 'Busqueda de Actas', description: 'Cualquier acta necesaria para el tramite', href: '#', icon: FingerPrintIcon },
  { name: 'Armado de Legajo', description: 'Apertura de la carpeta de ciudadania', href: '#', icon: SquaresPlusIcon },
  { name: 'Traducciones', description: 'Traduccion calificada con validez legal', href: '#', icon: ArrowPathIcon },
  { name: 'Legalizacion', description: 'Apostilla de documentos necesarios', href: '#', icon: CursorArrowRaysIcon },
]
const callsToAction = [
  { name: 'Introduccion', href: '#', icon: PlayCircleIcon },
  { name: 'Contactar', href: '#', icon: PhoneIcon },
]
const includedFeatures = [
  '1 hora de consultoria',
  'Por telefono o zoom',
  'Transcripcion de lo hablado durante la asesoria',
]

function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}

export default function Example() {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false)
  const [tab, setTab] = useState(0)

  return (
    <div className="">
      <header className="bg-white">
        <nav className="mx-auto flex max-w-7xl items-center justify-between p-6 lg:px-8" aria-label="Global">
          <div className="flex lg:flex-1">
            <a href="#" className="-m-1.5 p-1.5" onClick={()=>setTab(0)}>
              <span className="sr-only">projectOlivia</span>
              <img className="h-8 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=green&shade=600" alt="" />
            </a>
          </div>
          <div className="flex lg:hidden">
            <button
              type="button"
              className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-700"
              onClick={() => setMobileMenuOpen(true)}
            >
              <span className="sr-only">Open main menu</span>
              <Bars3Icon className="h-6 w-6" aria-hidden="true" />
            </button>
          </div>
          <Popover.Group className="hidden lg:flex lg:gap-x-12">
            <Popover className="relative">
              <Popover.Button className="flex items-center gap-x-1 text-sm font-semibold leading-6 text-gray-900">
                Servicios
                <ChevronDownIcon className="h-5 w-5 flex-none text-gray-400" aria-hidden="true" />
              </Popover.Button>

              <Transition
                as={Fragment}
                enter="transition ease-out duration-200"
                enterFrom="opacity-0 translate-y-1"
                enterTo="opacity-100 translate-y-0"
                leave="transition ease-in duration-150"
                leaveFrom="opacity-100 translate-y-0"
                leaveTo="opacity-0 translate-y-1"
              >
                <Popover.Panel className="absolute -left-8 top-full z-10 mt-3 w-screen max-w-md overflow-hidden rounded-3xl bg-white shadow-lg ring-1 ring-gray-900/5">
                  <div className="p-4">
                    {products.map((item) => (
                      <div
                        key={item.name}
                        className="group relative flex items-center gap-x-6 rounded-lg p-4 text-sm leading-6 hover:bg-gray-50"
                      >
                        <div className="flex h-11 w-11 flex-none items-center justify-center rounded-lg bg-gray-50 group-hover:bg-white">
                          <item.icon className="h-6 w-6 text-gray-600 group-hover:text-green-600" aria-hidden="true" />
                        </div>
                        <div className="flex-auto">
                          <a href={item.href} className="block font-semibold text-gray-900">
                            {item.name}
                            <span className="absolute inset-0" />
                          </a>
                          <p className="mt-1 text-gray-600">{item.description}</p>
                        </div>
                      </div>
                    ))}
                  </div>
                  <div className="grid grid-cols-2 divide-x divide-gray-900/5 bg-gray-50">
                    {callsToAction.map((item) => (
                      <a
                        key={item.name}
                        href={item.href}
                        className="flex items-center justify-center gap-x-2.5 p-3 text-sm font-semibold leading-6 text-gray-900 hover:bg-gray-100"
                      >
                        <item.icon className="h-5 w-5 flex-none text-gray-400" aria-hidden="true" />
                        {item.name}
                      </a>
                    ))}
                  </div>
                </Popover.Panel>
              </Transition>
            </Popover>
            <a onClick={()=>setTab(2)} href="#" className="text-sm font-semibold leading-6 text-gray-900">
              Comunidad
            </a>
            <a onClick={()=>setTab(1)} href="#" className="text-sm font-semibold leading-6 text-gray-900">
              Contacto
            </a>
          </Popover.Group>
          <div className="hidden lg:flex lg:flex-1 lg:justify-end">
            <button onClick={()=>setTab(3)} href="#" className="text-sm font-semibold leading-6 text-gray-900 hover:text-green-600 transition-all ease-out" data-modal-target="authentication-modal" data-modal-toggle="authentication-modal" type="button">
              Accede <span aria-hidden="true">&rarr;</span>
            </button>
          </div>
           
        </nav>
        <Dialog as="div" className="lg:hidden" open={mobileMenuOpen} onClose={setMobileMenuOpen}>
          <div className="fixed inset-0 z-10" />
          <Dialog.Panel className="fixed inset-y-0 right-0 z-10 w-full overflow-y-auto bg-white px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
            <div className="flex items-center justify-between">
              <a href="#" className="-m-1.5 p-1.5">
                <span className="sr-only">Your Company</span>
                <img
                  className="h-8 w-auto"
                  src="https://tailwindui.com/img/logos/mark.svg?color=green&shade=600"
                  alt=""
                />
              </a>
              <button
                type="button"
                className="-m-2.5 rounded-md p-2.5 text-gray-700"
                onClick={() => setMobileMenuOpen(false)}
              >
                <span className="sr-only">Close menu</span>
                <XMarkIcon className="h-6 w-6" aria-hidden="true" />
              </button>
            </div>
            <div className="mt-6 flow-root">
              <div className="-my-6 divide-y divide-gray-500/10">
                <div className="space-y-2 py-6">
                  <Disclosure as="div" className="-mx-3">
                    {({ open }) => (
                      <>
                        <Disclosure.Button className="flex w-full items-center justify-between rounded-lg py-2 pl-3 pr-3.5 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50">
                          Product
                          <ChevronDownIcon
                            className={classNames(open ? 'rotate-180' : '', 'h-5 w-5 flex-none')}
                            aria-hidden="true"
                          />
                        </Disclosure.Button>
                        <Disclosure.Panel className="mt-2 space-y-2">
                          {[...products, ...callsToAction].map((item) => (
                            <Disclosure.Button
                              key={item.name}
                              as="a"
                              href={item.href}
                              className="block rounded-lg py-2 pl-6 pr-3 text-sm font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                            >
                              {item.name}
                            </Disclosure.Button>
                          ))}
                        </Disclosure.Panel>
                      </>
                    )}
                  </Disclosure>
                  <a
                    href="#"
                    className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                  >
                    Features
                  </a>
                  <a
                    href="#"
                    className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                  >
                    Marketplace
                  </a>
                  <a
                    href="#"
                    className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                  >
                    Company
                  </a>
                </div>
                <div className="py-6">
                  <a
                    href="#"
                    className="-mx-3 block rounded-lg px-3 py-2.5 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                  >
                    Log in
                  </a>
                </div>
              </div>
            </div>
          </Dialog.Panel>
        </Dialog>
      </header>
      <link rel="stylesheet" href="https://demos.creative-tim.com/notus-js/assets/vendor/@fortawesome/fontawesome-free/css/all.min.css"/>

      {/* Home */}
      { tab==0 ?
      <div>
        <div className="relative overflow-hidden bg-white">
          <div className="pb-80 pt-16 sm:pb-40 sm:pt-24 lg:pb-48 lg:pt-40">
            <div className="relative mx-auto max-w-7xl px-4 sm:static sm:px-6 lg:px-8">
              <div className="sm:max-w-lg">
                <h1 className="text-4xl font-bold tracking-tight text-gray-900 sm:text-6xl">
                  Se el dueño de tu propio destino
                </h1>
                <p className="mt-4 text-xl text-gray-500">
                  En Olivia te ayudamos a obtener la ciudadania italiana de la manera mas rapida y eficiente posible
                </p>
              </div>
              <div>
                <div className="mt-10">
                  {/* Decorative image grid */}
                  <div
                    aria-hidden="true"
                    className="pointer-events-none lg:absolute lg:inset-y-0 lg:mx-auto lg:w-full lg:max-w-7xl"
                  >
                    <div className="absolute transform sm:left-1/2 sm:top-0 sm:translate-x-8 lg:left-1/2 lg:top-1/2 lg:-translate-y-1/2 lg:translate-x-8">
                      <div className="flex items-center space-x-6 lg:space-x-8">
                        <div className="grid flex-shrink-0 grid-cols-1 gap-y-6 lg:gap-y-8">
                          <div className="h-64 w-44 overflow-hidden rounded-lg sm:opacity-0 lg:opacity-100">
                            <img
                              src="https://images.unsplash.com/photo-1610016302534-6f67f1c968d8?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsYW5vfGVufDB8fDB8fHww&w=1000&q=80"
                              alt=""
                              className="h-full w-full object-cover object-center"
                            />
                          </div>
                          <div className="h-64 w-44 overflow-hidden rounded-lg">
                            <img
                              src="https://images4.alphacoders.com/100/1002541.jpg"
                              alt=""
                              className="h-full w-full object-cover object-center"
                            />
                          </div>
                        </div>
                        <div className="grid flex-shrink-0 grid-cols-1 gap-y-6 lg:gap-y-8">
                          <div className="h-64 w-44 overflow-hidden rounded-lg">
                            <img
                              src="https://www.iwanderlista.com/wp-content/uploads/2020/09/Venice-Italy-67-1027x685.jpg"
                              alt=""
                              className="h-full w-full object-cover object-center"
                            />
                          </div>
                          <div className="h-64 w-44 overflow-hidden rounded-lg">
                            <img
                              src="https://tourismmedia.italia.it/is/image/mitur/20210401173629-firenze-toscana-gettyimages-1145040590?wid=1600&hei=900&fit=constrain,1&fmt=webp"
                              alt=""
                              className="h-full w-full object-cover object-center"
                            />
                          </div>
                          <div className="h-64 w-44 overflow-hidden rounded-lg">
                            <img
                              src="https://www.campania.info/wp-content/uploads/sites/111/reggia-caserta-hd.jpg"
                              alt=""
                              className="h-full w-full object-cover object-center"
                            />
                          </div>
                        </div>
                        <div className="grid flex-shrink-0 grid-cols-1 gap-y-6 lg:gap-y-8">
                          <div className="h-64 w-44 overflow-hidden rounded-lg">
                            <img
                              src="https://cdn.getyourguide.com/img/location/5bfd5349892af.jpeg/68.jpg"
                              alt=""
                              className="h-full w-full object-cover object-center"
                            />
                          </div>
                          <div className="h-64 w-44 overflow-hidden rounded-lg">
                            <img
                              src="https://theplanetd.com/images/Best-Things-to-do-in-Palermo-sicily.jpg"
                              alt=""
                              className="h-full w-full object-cover object-center"
                            />
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <a
                    href="#"
                    className="inline-block rounded-md border border-transparent bg-green-600 px-8 py-3 text-center font-medium text-white hover:bg-green-700"
                  >
                    Cumplo los requisitos?
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="bg-white py-24 mb-0 sm:py-32">
          <div className="mx-auto max-w-7xl px-6 lg:px-8">
            <div className="mx-auto max-w-2xl lg:text-center">
              <h2 className="text-base font-semibold leading-7 text-green-600">Acelera el proceso</h2>
              <p className="mt-2 text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
                Todo lo que necesitas para obtener la ciudadania
              </p>
              <p className="mt-6 text-lg leading-8 text-gray-600">
                Te ayudamos a conseguir las actas, partidas, traducciones y certificaciones que necesites de manera rápida, efectiva y con garantía.
              </p>
            </div>
            <div className="mx-auto mt-16 max-w-2xl sm:mt-20 lg:mt-24 lg:max-w-4xl">
              <dl className="grid max-w-xl grid-cols-1 gap-x-8 gap-y-10 lg:max-w-none lg:grid-cols-2 lg:gap-y-16">
                {features.map((feature) => (
                  <div key={feature.name} className="relative pl-16">
                    <dt className="text-base font-semibold leading-7 text-gray-900">
                      <div className="absolute left-0 top-0 flex h-10 w-10 items-center justify-center rounded-lg bg-green-600">
                        <feature.icon className="h-6 w-6 text-white" aria-hidden="true" />
                      </div>
                      {feature.name}
                    </dt>
                    <dd className="mt-2 text-base leading-7 text-gray-600">{feature.description}</dd>
                  </div>
                ))}
              </dl>
            </div>
          </div>
        </div>  
        <div className="bg-white ">
        <div className="overflow-hidden bg-white py-24 sm:py-32">
            <div className="mx-auto max-w-7xl px-6 lg:px-8">
              <div className="mx-auto grid max-w-2xl grid-cols-1 gap-x-8 gap-y-16 sm:gap-y-20 lg:mx-0 lg:max-w-none lg:grid-cols-2">
                <div className="lg:pr-8 lg:pt-4">
                  <div className="lg:max-w-lg">
                    <h2 className="text-base font-semibold leading-7 text-green-600">Porque elegirnos</h2>
                    <p className="mt-2 text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">Extensa experiencia</p>
                    <p className="mt-6 text-lg leading-8 text-gray-600">En Olivia, formamos parte de una comunidad de personas como tú que han decidido elevar su calidad de vida al obtener la ciudadanía italiana. Hemos estado en tu lugar, compartiendo las mismas inquietudes y temores.</p>
                    <dl className="mt-10 max-w-xl space-y-8 text-base leading-7 text-gray-600 lg:max-w-none">
                      <div className="relative pl-9">
                        <dt className="inline font-semibold text-gray-900">
                          <svg className="absolute left-1 top-1 h-5 w-5 text-green-600" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                            <path fillRule="evenodd" d="M5.5 17a4.5 4.5 0 01-1.44-8.765 4.5 4.5 0 018.302-3.046 3.5 3.5 0 014.504 4.272A4 4 0 0115 17H5.5zm3.75-2.75a.75.75 0 001.5 0V9.66l1.95 2.1a.75.75 0 101.1-1.02l-3.25-3.5a.75.75 0 00-1.1 0l-3.25 3.5a.75.75 0 101.1 1.02l1.95-2.1v4.59z" clipRule="evenodd" />
                          </svg>
                          Metodologias Agiles 
                        </dt>
                        <dd className="inline"> Te ayudamos a agilizar al maximo el proceso de obtencion de documentos para conseguir la ciudadania italiana lo antes posible</dd>
                      </div>
                      <div className="relative pl-9">
                        <dt className="inline font-semibold text-gray-900">
                          <svg className="absolute left-1 top-1 h-5 w-5 text-green-600" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                            <path fillRule="evenodd" d="M10 1a4.5 4.5 0 00-4.5 4.5V9H5a2 2 0 00-2 2v6a2 2 0 002 2h10a2 2 0 002-2v-6a2 2 0 00-2-2h-.5V5.5A4.5 4.5 0 0010 1zm3 8V5.5a3 3 0 10-6 0V9h6z" clipRule="evenodd" />
                          </svg>
                          Plataforma Digital
                        </dt>
                        <dd className="inline"> Dentro nuestra plataforma podras gestir todos los tramites y documentaciones necesarias en un solo lugar</dd>
                      </div>
                      <div className="relative pl-9">
                        <dt className="inline font-semibold text-gray-900">
                          <svg className="absolute left-1 top-1 h-5 w-5 text-green-600" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                            <path d="M4.632 3.533A2 2 0 016.577 2h6.846a2 2 0 011.945 1.533l1.976 8.234A3.489 3.489 0 0016 11.5H4c-.476 0-.93.095-1.344.267l1.976-8.234z" />
                            <path fillRule="evenodd" d="M4 13a2 2 0 100 4h12a2 2 0 100-4H4zm11.24 2a.75.75 0 01.75-.75H16a.75.75 0 01.75.75v.01a.75.75 0 01-.75.75h-.01a.75.75 0 01-.75-.75V15zm-2.25-.75a.75.75 0 00-.75.75v.01c0 .414.336.75.75.75H13a.75.75 0 00.75-.75V15a.75.75 0 00-.75-.75h-.01z" clipRule="evenodd" />
                          </svg>
                          Asesoria 24/7
                        </dt>
                        <dd className="inline"> Disponibilidad maxima en el seguimiento del proceso de todos nuestros clientes</dd>
                      </div>
                    </dl>
                  </div>
                </div>
                <img src="https://lp-cms-production.imgix.net/2021-07/Tourists%20Return%20To%20Sicily%27s%20Taormina%20With%20Eased%20Covid-19%20Restrictions.jpg?auto=format&w=1440&h=810&fit=crop&q=75" alt="Product screenshot" className="w-[48rem] max-w-none rounded-xl shadow-xl ring-1 ring-gray-400/10 sm:w-[57rem] md:-ml-4 lg:-ml-0" width="2432" height="1442"/>
              </div>
            </div>
          </div>
        <div className=" bg-white radius-for-skewed">
              <div className="container mx-auto px-4">
                  <div className="flex flex-wrap -mx-4">
                      <div className="w-full md:w-1/2 lg:w-1/3 px-4 mb-8 lg:mb-0">
                          <div className="p-8 bg-white shadow rounded border-0  cursor-pointer">
                              <h4 className="mb-2 text-2xl font-bold font-heading text-green-500">Traducciones y Legalizaciones</h4> <span className="text-6xl font-bold text-green-500">$89+</span>
                              <p className="mt-3 mb-6 text-gray-500 leading-loose ">Legalizacion de documentos previamente obtenidos o enviados mediante nuestra plataforma web</p>
                              <ul className="mb-6 text-gray-500">
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-blue-600" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="rgb(34,197,94)">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Red de abogados y traductores</span> </li>
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-blue-600" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="rgb(34,197,94)">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Apostilla de documentos para la ciudadania</span> </li>
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-blue-600" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="rgb(34,197,94)">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Legalizacion de licencia de conducir</span> </li>
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-blue-600" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="rgb(34,197,94)">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span data-config-id="01_benefit4">Resolucion rapida con entrega en pocos dias</span> </li>
                              </ul> <a className="inline-block text-center py-2 px-4 w-full rounded-l-xl rounded-t-xl bg-green-500 hover:bg-green-700 text-white font-bold leading-loose transition duration-200" href="#">Continuar</a>
                          </div>
                      </div>
                      <div className="w-full md:w-1/2 lg:w-1/3 px-4 mb-8 lg:mb-0">
                          <div className="p-8 bg-green-500 shadow rounded">
                              <h4 className="mb-2 text-2xl font-bold text-white" data-config-id="02_title">Armado de carpeta</h4> <span className="text-6xl font-bold text-white" data-config-id="02_price">$299</span>
                              <p className="mt-3 mb-6 leading-loose text-gray-50" data-config-id="02_desc">Inicia o continua el tramite de entrega de actas para la obtencion de la ciudadania italiana</p>
                              <ul className="mb-6 text-gray-50">
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-white-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Evaluacion personal de los documentos a entregar</span> </li>
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-white-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Control de dicha documentacion a fin de agilizar el proceso</span> </li>
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-white-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Inicio formal del tramite de ciudadania mediante la apertura del proceso</span> </li>
                                  
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-white-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Asesoria caso a caso para delimitar los documentos a obtener</span> </li>
                              </ul> <a className="inline-block text-center py-2 px-4 w-full rounded-l-xl rounded-t-xl bg-green-700 hover:bg-gray-50 hover:text-green-700 font-bold leading-loose transition duration-200" href="#" data-config-id="02_primary-action">Iniciar Tramite</a>
                          </div>
                      </div>
                      <div className="w-full lg:w-1/3 px-4">
                          <div className="p-8 bg-white shadow rounded">
                              <h4 className="mb-2 text-2xl font-bold font-heading text-green-700" data-config-id="03_title">Busqueda de Actas</h4> <span className="text-6xl font-bold text-green-700" data-config-id="03_price">$110</span> <span className="text-gray-400 text-xs text-green-700" data-config-id="03_note">/acta</span>
                              <p className="mt-3 mb-6 text-gray-500 leading-loose" data-config-id="03_desc">Actas de nacimiento, matrimonio o defuncion a retirar en el comune correspondiente</p>
                              <ul className="mb-6 text-gray-500">
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-blue-600" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="rgb(34,197,94)">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Contacto con el comune</span> </li>
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-blue-600" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="rgb(34,197,94)">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Retiro de actas en persona</span> </li>
                                  <li className="mb-2 flex"> <svg className="mr-2 w-5 h-5 text-blue-600" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="rgb(34,197,94)">
                                          <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd"></path>
                                      </svg> <span>Envio mediante carta firmada</span> </li>
                              </ul> <a className="inline-block text-center py-2 px-4 w-full rounded-l-xl rounded-t-xl bg-green-500 hover:bg-green-700 text-white font-bold leading-loose transition duration-200" href="#" data-config-id="03_primary-action">Continuar</a>
                          </div>
                      </div>
                  </div>
              </div>
        </div>
        <div className="mx-auto max-w-7xl px-6 lg:px-8">
          <div className="mx-auto mt-16 max-w-2xl rounded-3xl ring-1 ring-gray-200 sm:mt-20 lg:mx-0 lg:flex lg:max-w-none">
            <div className="p-8 sm:p-10 lg:flex-auto">
              <h3 className="text-2xl font-bold tracking-tight text-gray-900">Asesoria en tramites migratorios</h3>
              <p className="mt-6 text-base leading-7 text-gray-600">
                Agenda un horario y coordina una llamda/videochat con uno de nuestros expertos para realizar cualquier pregunta referente al tramite de obtencion de la ciudadania, pasaporte u otras dudas que puedas llegar a tener.
              </p>
              <div className="mt-10 flex items-center gap-x-4">
                <h4 className="flex-none text-sm font-semibold leading-6 text-green-600">Incluye</h4>
                <div className="h-px flex-auto bg-gray-100" />
              </div>
              <ul
                role="list"
                className="mt-8 grid grid-cols-1 gap-4 text-sm leading-6 text-gray-600 sm:grid-cols-2 sm:gap-6"
              >
                {includedFeatures.map((feature) => (
                  <li key={feature} className="flex gap-x-3">
                    <CheckIcon className="h-6 w-5 flex-none text-green-600" aria-hidden="true" />
                    {feature}
                  </li>
                ))}
              </ul>
            </div>
            <div className="-mt-2 p-2 lg:mt-0 lg:w-full lg:max-w-md lg:flex-shrink-0">
              <div className="rounded-2xl bg-gray-50 py-10 text-center ring-1 ring-inset ring-gray-900/5 lg:flex lg:flex-col lg:justify-center lg:py-16">
                <div className="mx-auto max-w-xs px-8">
                  <p className="text-base font-semibold text-gray-600">Pago Unico</p>
                  <p className="mt-6 flex items-baseline justify-center gap-x-2">
                    <span className="text-5xl font-bold tracking-tight text-gray-900">$99</span>
                    <span className="text-sm font-semibold leading-6 tracking-wide text-gray-600">USD</span>
                  </p>
                  <a
                    href="#"
                    className="mt-10 block w-full rounded-md bg-green-600 px-3 py-2 text-center text-sm font-semibold text-white shadow-sm hover:bg-green-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
                  >
                    Continuar
                  </a>
                  <p className="mt-6 text-xs leading-5 text-gray-600">
                    Recibo disponible bajo consulta previa del cliente
                  </p>
                </div>
              </div>
            </div>
          </div>
          </div>
        </div></div> : ""}

      {/* Contact */}
      { tab==1 ?
      <div>
        <div className=" bg-white px-6 py-24 sm:py-32 lg:px-8">
          <div className="mx-auto max-w-2xl text-center">
            <h2 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">Contactate con nosotros</h2>
            <p className="mt-2 text-lg leading-8 text-gray-600">Utilizar este formulario para cualquier duda en torno a nuestros servicios y productos. </p>
            <p className="mt-2 text-lg leading-8 text-gray-600">Tiempo de respuesta estimado: 2 horas</p>
          </div>
          <form action="#" method="POST" className="mx-auto mt-16 max-w-xl sm:mt-20">
            <div className="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
              <div>
                <label  className="block text-sm font-semibold leading-6 text-gray-900">Nombre</label>
                <div className="mt-2.5">
                  <input type="text" name="first-name" id="first-name"  className=" bg-white block w-full rounded-md border-0 px-3.5 py-2 text-green-900 shadow-sm ring-1 ring-inset ring-green-300 placeholder:text-green-400 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-green-600 sm:text-sm sm:leading-6"/>
                </div>
              </div>
              <div>
                <label  className="block text-sm font-semibold leading-6 text-gray-900">Apellido</label>
                <div className="mt-2.5">
                  <input type="text" name="last-name" id="last-name"  className="bg-white block w-full rounded-md border-0 px-3.5 py-2 text-green-900 shadow-sm ring-1 ring-inset ring-green-300 placeholder:text-green-400 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-green-600 sm:text-sm sm:leading-6"/>
                </div>
              </div>
              <div className="sm:col-span-2">
                <label  className="block text-sm font-semibold leading-6 text-gray-900">Email</label>
                <div className="mt-2.5">
                  <input type="email" name="email" id="email" className="bg-white block w-full rounded-md border-0 px-3.5 py-2 text-green-900 shadow-sm ring-1 ring-inset ring-green-300 placeholder:text-green-400 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-green-600 sm:text-sm sm:leading-6"/>
                </div>
              </div>
              <div className="sm:col-span-2">
                <label  className="block text-sm font-semibold leading-6 text-gray-900">Mensaje</label>
                <div className="mt-2.5">
                  <textarea name="message" id="message" rows={4} className="bg-white block w-full rounded-md border-0 px-3.5 py-2 text-green-900 shadow-sm ring-1 ring-inset ring-green-300 placeholder:text-green-400 focus:ring-2 focus:ring-inset focus:ring-green-600 sm:text-sm sm:leading-6"></textarea>
                </div>
              </div>
              <div className="flex gap-x-4 sm:col-span-2">
                <div className="flex h-6 items-center">
                  <button type="button" className="bg-gray-200 flex w-8 flex-none cursor-pointer rounded-full p-px ring-1 ring-inset ring-green-900/5 transition-colors duration-200 ease-in-out focus-visible:outline-none focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600" role="switch" aria-checked="false" aria-labelledby="switch-1-label">
                    <span className="sr-only">Acepto los terminos</span>
                    <span aria-hidden="true" className="translate-x-0 h-4 w-4 transform rounded-full bg-white shadow-sm ring-1 ring-green-900/5 transition duration-200 ease-in-out"></span>
                  </button>
                </div>
                <label className="text-sm leading-6 text-gray-600" id="switch-1-label">
                  Acepto las siguientes 
                  <a href="#" className="font-semibold text-green-600"> politicas de privacidad</a>.
                </label>
              </div>
            </div>
            <div className="mt-10">
              <button type="submit" className="block w-full rounded-md bg-green-600 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-green-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600">Enviar</button>
            </div>
          </form>
        </div></div> : ""}

      {/* Community */}
      { tab==2 ?
      <div className="bg-white">
        <div className="bg-white">
          <div className="mx-auto grid max-w-2xl grid-cols-1 items-center gap-x-8 gap-y-16 px-4 py-24 sm:px-6 sm:py-32 lg:max-w-7xl lg:grid-cols-2 lg:px-8">
            <div>
              <h2 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">Comunidad</h2>
              <p className="mt-4 text-gray-500">Blog de dudas, preguntas y respuestas sobre todo lo relacionado con la mudanza hacia Italia, con videos exclusivos.</p>

              <dl className="mt-16 grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 sm:gap-y-16 lg:gap-x-8">
                <div className="border-t border-gray-200 pt-4">
                  <dt className="font-medium text-gray-900">Blog y Social Media</dt>
                  <dd className="mt-2 text-sm text-gray-500">Interactua con nosotros mediante nuestras redes sociales, o despeja tus dudas dentro nuestro Blog</dd>
                </div>
                <div className="border-t border-gray-200 pt-4">
                  <dt className="font-medium text-gray-900">Videos Interactivos</dt>
                  <dd className="mt-2 text-sm text-gray-500">Solid walnut base with rare earth magnets and powder coated steel card cover</dd>
                </div>
              </dl>
            </div>
            <div className="grid grid-cols-2 grid-rows-2 gap-4 sm:gap-6 lg:gap-8">
              <img src="https://images.unsplash.com/photo-1610016302534-6f67f1c968d8?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsYW5vfGVufDB8fDB8fHww&w=1000&q=80" alt=" ." className="rounded-lg bg-gray-100"/>
              <img src="https://tourismmedia.italia.it/is/image/mitur/20210401173629-firenze-toscana-gettyimages-1145040590?wid=1600&hei=900&fit=constrain,1&fmt=webp" alt=" ." className="rounded-lg bg-gray-100"/>
              <img src="https://theplanetd.com/images/Best-Things-to-do-in-Palermo-sicily.jpg" alt="." className="rounded-lg bg-gray-100"/>
              <img src="https://cdn.getyourguide.com/img/location/5bfd5349892af.jpeg/68.jpg" alt="." className="rounded-lg bg-gray-100"/>
            </div>
          </div>
        </div>
        <h2 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl text-center mx-auto mb-8">Datos Importantes</h2>
        <div className="w-fit flex gap-4 container mx-auto">
          <button className="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-green-400 to-blue-600 group-hover:from-green-400 group-hover:to-blue-600 hover:text-white  focus:ring-4 focus:outline-none focus:ring-green-200 dark:focus:ring-green-800">
            <span className="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white font-medium rounded-md group-hover:bg-opacity-0">
                Tramites
            </span>
          </button>
          <button className="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-pink-500 to-orange-400 group-hover:from-pink-500 group-hover:to-orange-400 hover:text-white  focus:ring-4 focus:outline-none focus:ring-pink-200 dark:focus:ring-pink-800">
            <span className="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white  rounded-md group-hover:bg-opacity-0">
                Vida Cotidiana
            </span>
          </button>
          <button className="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white  focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
            <span className="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white  rounded-md group-hover:bg-opacity-0">
                Leyes y Regulaciones
            </span>
          </button>
          <button className="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-purple-600 to-blue-500 group-hover:from-purple-600 group-hover:to-blue-500 hover:text-white  focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
            <span className="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white  rounded-md group-hover:bg-opacity-0">
                Otros...
            </span>
          </button>
        </div>
        <div className="w-2/5 grid grid-cols-2 md:grid-cols-3 gap-4 py-12 mx-auto">
          <div className="bg-green">
            <a href="#" className="block max-w-sm p-6 bg-white border border-sky-200 rounded-md shadow bg-gradient-to-br from-green-400 to-blue-600 group-hover:from-green-400 group-hover:to-blue-600 hover:opacity-[.75] transition-all ease-in duration-50" >
                <p className=" font-medium   py-0.5 rounded ">01/01/2023 <span className="bg-green-100 text-green-800 text-xs font-medium  px-2.5 py-0.5 rounded dark:bg-green-600 dark:text-green-300 float-right">TRAMITES</span></p>
                <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white ">Cual es el primer paso para iniciar el proceso de ciudadania?</h5>
            </a>
          </div>
          <div className="bg-green">
            <a href="#" className="block max-w-sm p-6 bg-white border border-red-200 rounded-lg shadow bg-gradient-to-br from-pink-500 to-orange-400 group-hover:from-pink-500 group-hover:to-orange-400 hover:opacity-[.75] transition-all ease-in duration-50">
                <p className=" font-medium   py-0.5 rounded ">04/06/2023 <span className="bg-red-100 text-red-800 text-xs font-medium  px-2.5 py-0.5 rounded dark:bg-pink-600 dark:text-red-300 float-right">VIDA COTIDIANA</span></p>
                <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white ">Cual es el primer paso para iniciar el proceso de ciudadania?</h5>
            </a>
          </div>
          <div className="bg-green">
            <a href="#" className="block max-w-sm p-6 bg-white border border-sky-200 rounded-md shadow bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:opacity-[.75] transition-all ease-in duration-50">
                <p className=" font-medium   py-0.5 rounded ">06/09/2023 <span className="bg-sky-100 text-sky-800 text-xs font-medium  px-2.5 py-0.5 rounded dark:bg-sky-700 dark:text-sky-300 float-right">LEYES Y REGULACIONES</span></p>
                <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white ">Cual es el primer paso para iniciar el proceso de ciudadania?</h5>
            </a>
          </div>
          <div className="bg-green">
            <a href="#" className="block max-w-sm p-6 bg-white border border-sky-200 rounded-md shadow bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:opacity-[.75] transition-all ease-in duration-50">
                <p className=" font-medium   py-0.5 rounded ">06/09/2023 <span className="bg-sky-100 text-sky-800 text-xs font-medium  px-2.5 py-0.5 rounded dark:bg-sky-700 dark:text-sky-300 float-right">LEYES Y REGULACIONES</span></p>
                <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white ">Cual es el primer paso para iniciar el proceso de ciudadania?</h5>
            </a>
          </div>
          <div className="bg-green">
            <a href="#" className="block max-w-sm p-6 bg-white border border-sky-200 rounded-md shadow bg-gradient-to-br from-purple-600 to-blue-500 group-hover:from-purple-600 group-hover:to-blue-500 hover:opacity-[.75] transition-all ease-in duration-50">
                <p className=" font-medium   py-0.5 rounded ">01/01/2023 <span className="bg-green-100 text-green-800 text-xs font-medium  px-2.5 py-0.5 rounded dark:bg-purple-600 dark:text-purple-300 float-right">Otros...</span></p>
                <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white ">Cual es el primer paso para iniciar el proceso de ciudadania?</h5>
            </a>
          </div>
          <div className="bg-green">
            <a href="#" className="block max-w-sm p-6 bg-white border border-sky-200 rounded-md shadow bg-gradient-to-br from-purple-600 to-blue-500 group-hover:from-purple-600 group-hover:to-blue-500 hover:opacity-[.75] transition-all ease-in duration-50">
                <p className=" font-medium   py-0.5 rounded ">01/01/2023 <span className="bg-green-100 text-green-800 text-xs font-medium  px-2.5 py-0.5 rounded dark:bg-purple-600 dark:text-purple-300 float-right">Otros...</span></p>
                <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white ">Cual es el primer paso para iniciar el proceso de ciudadania?</h5>
            </a>
          </div>
          <div className="bg-green">
            <a href="#" className="block max-w-sm p-6 bg-white border border-red-200 rounded-lg shadow bg-gradient-to-br from-pink-500 to-orange-400 group-hover:from-pink-500 group-hover:to-orange-400 hover:opacity-[.75] transition-all ease-in duration-50">
                <p className=" font-medium   py-0.5 rounded ">04/06/2023 <span className="bg-red-100 text-red-800 text-xs font-medium  px-2.5 py-0.5 rounded dark:bg-pink-600 dark:text-red-300 float-right">VIDA COTIDIANA</span></p>
                <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white ">Cual es el primer paso para iniciar el proceso de ciudadania?</h5>
            </a>
          </div>
          <div className="bg-green">
            <a href="#" className="block max-w-sm p-6 bg-white border border-sky-200 rounded-md shadow bg-gradient-to-br from-green-400 to-blue-600 group-hover:from-green-400 group-hover:to-blue-600 hover:opacity-[.75] transition-all ease-in duration-50" >
                <p className=" font-medium   py-0.5 rounded ">01/01/2023 <span className="bg-green-100 text-green-800 text-xs font-medium  px-2.5 py-0.5 rounded dark:bg-green-600 dark:text-green-300 float-right">TRAMITES</span></p>
                <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white ">Cual es el primer paso para iniciar el proceso de ciudadania?</h5>
            </a>
          </div>
        </div>
        <iframe className="w-2/5  max-w-full border border-gray-200 rounded-lg dark:border-gray-700 mx-auto" width="560" height="500" src="https://www.youtube.com/embed/Z97Odil2NwQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe></div> : ""}

      {/* Login */}
      { tab==3 ?
      <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8 bg-white">
        <div class="sm:mx-auto sm:w-full sm:max-w-sm">
          <img class="mx-auto h-10 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=green&shade=600" alt="Your Company"/>
          <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Accede a nuestra plataforma</h2>
        </div>

        <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form class="space-y-6" action="#" method="POST">
            <div>
              <label  class="block text-sm font-medium leading-6 text-gray-900">Email</label>
              <div class="mt-2">
                <input id="email" name="email" type="email" autocomplete="email" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div>
              <div class="flex items-center justify-between">
                <label class="block text-sm font-medium leading-6 text-gray-900">Contraseña</label>
                <div class="text-sm">
                  <a onClick={()=>setTab(5)} href="#" class="font-semibold text-green-600 hover:text-green-500">Olvidaste tu contraseña?</a>
                </div>
              </div>
              <div class="mt-2">
                <input id="password" name="password" type="password" autocomplete="current-password" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div>
              <button type="submit" class="flex w-full justify-center rounded-md bg-green-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-500 transition-all ease-out focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Acceder</button>
            </div>
          </form>

          <p class="mt-10 text-center text-sm text-gray-500">
            No eres miembro?
            <a onClick={()=>setTab(4)} href="#" class="font-semibold leading-6 text-green-600 hover:text-green-500 transition-all ease-out"> Registrate ahora, es gratis!</a>
          </p>
        </div></div> : ""}

      {/* Register */}
      { tab==4 ?
      <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8 bg-white">
        <div class="sm:mx-auto sm:w-full sm:max-w-sm">
          <img class="mx-auto h-10 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=green&shade=600" alt="Your Company"/>
          <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Registrarse ahora</h2>
        </div>

        <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form class="space-y-6" action="#" method="POST">
            <div>
              <label  class="block text-sm font-medium leading-6 text-gray-900">Email</label>
              <div class="mt-2">
                <input id="email" name="email" type="email" autocomplete="email" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div>
              <div class="flex items-center justify-between">
                <label class="block text-sm font-medium leading-6 text-gray-900">Contraseña</label>
                <div class="text-sm">
                </div>
              </div>
              <div class="mt-2">
                <input id="password" name="password" type="password" autocomplete="current-password" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div>
              <div class="flex items-center justify-between">
                <label class="block text-sm font-medium leading-6 text-gray-900">Repetir contraseña</label>
                <div class="text-sm">
                </div>
              </div>
              <div class="mt-2">
                <input id="password_confirm" name="password_confirm" type="password" autocomplete="current-password" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>
          <div class="flex p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-black-100 dark:text-red-400" role="alert">
            <svg aria-hidden="true" class="flex-shrink-0 inline w-5 h-5 mr-3" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd"></path></svg>
            <span class="sr-only">Danger</span>
            <div>
              <span class="font-medium">Por motivos de seguridad, tu contraseña debe:</span>
                <ul class="mt-1.5 ml-4 list-disc list-inside">
                  <li>Tener minimo 8 caracteres</li>
                  <li>Tener minimo una letra y un numero</li>
                  <li>Incluir un caracter especial, como ! @ # ?</li>
              </ul>
            </div>
          </div>

            <div>
              <button type="submit" class="flex w-full justify-center rounded-md bg-green-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-500 transition-all ease-out focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Registrarse</button>
            </div>
          </form>

          <p class="mt-10 text-center text-sm text-gray-500">
            Ya estas registrado?
            <a onClick={()=>setTab(3)} href="#" class="font-semibold leading-6 text-green-600 hover:text-green-500 transition-all ease-out"> Accede ahora!</a>
          </p>
        </div>
        <div id="alert-1" class="flex w-1/3 p-4 mb-4 text-green-800 rounded-lg bg-green-50 dark:bg-green-800 dark:text-green-400 mx-auto my-4" role="alert">
          <svg aria-hidden="true" class="flex-shrink-0 w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd"></path></svg>
          <span class="sr-only">Info</span>
          <div class="ml-3 text-sm font-medium">
            Chequea tu casilla de email por un  <span href="#" class="font-semibold underline hover:no-underline">email de verificacion</span> nuestro. Haz click en link que has recibido para tener acceso.
          </div>
            <button type="button" class="ml-auto -mx-1.5 -my-1.5 bg-green-50 text-green-500 rounded-lg focus:ring-2 focus:ring-green-400 hover:cursor-pointer p-1.5 hover:bg-green-200 inline-flex h-8 w-8 dark:bg-green-800 dark:text-green-400 dark:hover:bg-green-700" data-dismiss-target="#alert-1" aria-label="Close">
              <span class="sr-only">Close</span>
              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
          </button>
        </div></div> : ""}

      {/* Recover Password */}
      { tab==5 ?
      <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8 bg-white">
        <div class="sm:mx-auto sm:w-full sm:max-w-sm">
          <img class="mx-auto h-10 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=green&shade=600" alt="Your Company"/>
          <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Olvide mi contraseña</h2>
        </div>

        <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form class="space-y-6" action="#" method="POST">
            <div>
              <label  class="block text-sm font-medium leading-6 text-gray-900">Email</label>
              <div class="mt-2">
                <input id="email" name="email" type="email" autocomplete="email" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>


            <div>
              <button type="submit" class="flex w-full justify-center rounded-md bg-green-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-500 transition-all ease-out focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Continuar</button>
            </div>
          </form>

          <p class="mt-10 text-center text-sm text-gray-500">
            No eres miembro?
            <a onClick={()=>setTab(4)} href="#" class="font-semibold leading-6 text-green-600 hover:text-green-500 transition-all ease-out"> Registrate ahora, es gratis!</a>
          </p>
        </div></div> : ""}

      {/* New Password */}
      { tab==6 ?
      <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8 bg-white">
        <div class="sm:mx-auto sm:w-full sm:max-w-sm">
          <img class="mx-auto h-10 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=green&shade=600" alt="Your Company"/>
          <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Olvide mi contraseña</h2>
        </div>

        <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form class="space-y-6" action="#" method="POST">
            <div>
              <div class="flex items-center justify-between">
                <label class="block text-sm font-medium leading-6 text-gray-900">Nueva Contraseña</label>
                <div class="text-sm">
                </div>
              </div>
              <div class="mt-2">
                <input id="password" name="password" type="password" autocomplete="current-password" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div>
              <div class="flex items-center justify-between">
                <label class="block text-sm font-medium leading-6 text-gray-900">Repetir contraseña</label>
                <div class="text-sm">
                </div>
              </div>
              <div class="mt-2">
                <input id="password_confirm" name="password_confirm" type="password" autocomplete="current-password" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>


            <div>
              <button type="submit" class="flex w-full justify-center rounded-md bg-green-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-green-500 transition-all ease-out focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Continuar</button>
            </div>
          </form>

          <p class="mt-10 text-center text-sm text-gray-500">
            No eres miembro?
            <a onClick={()=>setTab(4)} href="#" class="font-semibold leading-6 text-green-600 hover:text-green-500 transition-all ease-out"> Registrate ahora, es gratis!</a>
          </p>
        </div></div> : ""}


      {/* Footer */}
      <section className="bg-white">
        <div className="max-w-screen-xl px-4 py-12 mx-auto space-y-8 overflow-hidden sm:px-6 lg:px-8">
            <nav className="flex flex-wrap justify-center -mx-5 -my-2">
                <div className="px-5 py-2">
                    <a href="#" className="text-base leading-6 text-gray-500 hover:text-gray-900">
                        Inicio
                    </a>
                </div>
                <div className="px-5 py-2">
                    <a href="#" className="text-base leading-6 text-gray-500 hover:text-gray-900">
                        Busqueda de Actas
                    </a>
                </div>
                <div className="px-5 py-2">
                    <a href="#" className="text-base leading-6 text-gray-500 hover:text-gray-900">
                        Traducciones
                    </a>
                </div>
                <div className="px-5 py-2">
                    <a href="#" className="text-base leading-6 text-gray-500 hover:text-gray-900">
                        Legalizaciones
                    </a>
                </div>
                <div className="px-5 py-2">
                    <a href="#" className="text-base leading-6 text-gray-500 hover:text-gray-900">
                        Comunidad
                    </a>
                </div>
                <div className="px-5 py-2">
                    <a onClick={()=>setTab(1)} href="#" className="text-base leading-6 text-gray-500 hover:text-gray-900">
                        Contacto
                    </a>
                </div>
            </nav>
            <div className="flex justify-center mt-8 space-x-6">
                <a href="#" className="text-gray-400 hover:text-gray-500">
                    <span className="sr-only">Facebook</span>
                    <svg className="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 24 24">
                        <path fillRule="evenodd" d="M22 12c0-5.523-4.477-10-10-10S2 6.477 2 12c0 4.991 3.657 9.128 8.438 9.878v-6.987h-2.54V12h2.54V9.797c0-2.506 1.492-3.89 3.777-3.89 1.094 0 2.238.195 2.238.195v2.46h-1.26c-1.243 0-1.63.771-1.63 1.562V12h2.773l-.443 2.89h-2.33v6.988C18.343 21.128 22 16.991 22 12z" clipRule="evenodd"></path>
                    </svg>
                </a>
                <a href="#" className="text-gray-400 hover:text-gray-500">
                    <span className="sr-only">Instagram</span>
                    <svg className="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 24 24">
                        <path fillRule="evenodd" d="M12.315 2c2.43 0 2.784.013 3.808.06 1.064.049 1.791.218 2.427.465a4.902 4.902 0 011.772 1.153 4.902 4.902 0 011.153 1.772c.247.636.416 1.363.465 2.427.048 1.067.06 1.407.06 4.123v.08c0 2.643-.012 2.987-.06 4.043-.049 1.064-.218 1.791-.465 2.427a4.902 4.902 0 01-1.153 1.772 4.902 4.902 0 01-1.772 1.153c-.636.247-1.363.416-2.427.465-1.067.048-1.407.06-4.123.06h-.08c-2.643 0-2.987-.012-4.043-.06-1.064-.049-1.791-.218-2.427-.465a4.902 4.902 0 01-1.772-1.153 4.902 4.902 0 01-1.153-1.772c-.247-.636-.416-1.363-.465-2.427-.047-1.024-.06-1.379-.06-3.808v-.63c0-2.43.013-2.784.06-3.808.049-1.064.218-1.791.465-2.427a4.902 4.902 0 011.153-1.772A4.902 4.902 0 015.45 2.525c.636-.247 1.363-.416 2.427-.465C8.901 2.013 9.256 2 11.685 2h.63zm-.081 1.802h-.468c-2.456 0-2.784.011-3.807.058-.975.045-1.504.207-1.857.344-.467.182-.8.398-1.15.748-.35.35-.566.683-.748 1.15-.137.353-.3.882-.344 1.857-.047 1.023-.058 1.351-.058 3.807v.468c0 2.456.011 2.784.058 3.807.045.975.207 1.504.344 1.857.182.466.399.8.748 1.15.35.35.683.566 1.15.748.353.137.882.3 1.857.344 1.054.048 1.37.058 4.041.058h.08c2.597 0 2.917-.01 3.96-.058.976-.045 1.505-.207 1.858-.344.466-.182.8-.398 1.15-.748.35-.35.566-.683.748-1.15.137-.353.3-.882.344-1.857.048-1.055.058-1.37.058-4.041v-.08c0-2.597-.01-2.917-.058-3.96-.045-.976-.207-1.505-.344-1.858a3.097 3.097 0 00-.748-1.15 3.098 3.098 0 00-1.15-.748c-.353-.137-.882-.3-1.857-.344-1.023-.047-1.351-.058-3.807-.058zM12 6.865a5.135 5.135 0 110 10.27 5.135 5.135 0 010-10.27zm0 1.802a3.333 3.333 0 100 6.666 3.333 3.333 0 000-6.666zm5.338-3.205a1.2 1.2 0 110 2.4 1.2 1.2 0 010-2.4z" clipRule="evenodd"></path>
                    </svg>
                </a>
                <a href="#" className="text-gray-400 hover:text-gray-500">
                    <span className="sr-only">Twitter</span>
                    <svg className="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 24 24">
                        <path d="M8.29 20.251c7.547 0 11.675-6.253 11.675-11.675 0-.178 0-.355-.012-.53A8.348 8.348 0 0022 5.92a8.19 8.19 0 01-2.357.646 4.118 4.118 0 001.804-2.27 8.224 8.224 0 01-2.605.996 4.107 4.107 0 00-6.993 3.743 11.65 11.65 0 01-8.457-4.287 4.106 4.106 0 001.27 5.477A4.072 4.072 0 012.8 9.713v.052a4.105 4.105 0 003.292 4.022 4.095 4.095 0 01-1.853.07 4.108 4.108 0 003.834 2.85A8.233 8.233 0 012 18.407a11.616 11.616 0 006.29 1.84"></path>
                    </svg>
                </a>
            </div>
            <p className="mt-8 text-base leading-6 text-center text-gray-400">
                © 2023 projectOlivia All rights reserved.
            </p>
        </div> </section>

    </div>
  )
}
